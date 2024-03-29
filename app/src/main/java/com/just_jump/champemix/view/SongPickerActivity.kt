package com.just_jump.champemix.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.just_jump.champemix.databinding.ActivitySongPickerBinding
import com.just_jump.champemix.model.GeneralSetting
import com.just_jump.champemix.presenter.SongPickerPresenter
import com.just_jump.champemix.presenter.adapter.RecycleViewAdapterSong
import com.just_jump.champemix.presenter.tools.MyPlayerSong
import com.just_jump.champemix.utility.LoadSong
import com.just_jump.champemix.utility.PlayerSample

class SongPickerActivity : AppCompatActivity(), SongPickerPresenter.View, LoadSong, PlayerSample {

    private var songPickerPresenter = SongPickerPresenter()
    private var song: MyPlayerSong? = null
    private var currentId = 0
    lateinit var binding: ActivitySongPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // check theme of the application and set the previous one
        checkTheme(applicationContext)

        super.onCreate(savedInstanceState)
        binding = ActivitySongPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialization of the presenter
        songPickerPresenter.onCreate(this, applicationContext)

        binding.backSetting.setOnClickListener {
            finish()
        }
    }

    private fun checkTheme(context: Context) {
        // check the general setting
        val configData = GeneralSetting().customPreference(context).getData()

        if (configData!!.Theme) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        }
    }

    // load de information from the presenter to prepare de list of music
    override fun loadData(dataList: ArrayList<String>) {
        binding.recyclerviewFields.layoutManager = GridLayoutManager(this,2)
        binding.recyclerviewFields.adapter = RecycleViewAdapterSong(
            applicationContext,
            dataList,
            this,
            this
        )
    }

    override fun recycleViewControlEventSong(songName: String) {
        val resultIntent = Intent()
        resultIntent.putExtra("packetDataSong", songName)
        setResult(RESULT_OK,resultIntent)

        finish()
    }

    // function to hide the navigationBar and statusBar and leave float
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    override fun playSong(idResource: Int) {
        if (currentId == 0){
            song = MyPlayerSong(applicationContext, idResource)
            song!!.play()
            currentId = idResource
        } else if (currentId == idResource){
            if (song!!.isPlaying()){
                song!!.pause()
            } else {
                song!!.play()
            }
        } else {
            song!!.onDestroy()
            song = MyPlayerSong(applicationContext, idResource)
            song!!.play()
            currentId = idResource
        }
    }

    override fun onDestroy() {
        if (song != null){
            song!!.onDestroy()
        }
        songPickerPresenter.onDestroy()
        super.onDestroy()
    }
}