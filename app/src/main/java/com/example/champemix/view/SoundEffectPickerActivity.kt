package com.example.champemix.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.example.champemix.databinding.ActivityPickerSoundEffectBinding
import com.example.champemix.presenter.PickerSoundEffectPresenter
import com.example.champemix.presenter.adapter.RecycleViewAdapterSoundEffect
import com.example.champemix.utility.GeneralSettingData
import com.example.champemix.utility.LoadEffectSound

class SoundEffectPickerActivity : AppCompatActivity(), PickerSoundEffectPresenter.View, LoadEffectSound {

    private var buttonNumber: Int = 0
    private val pickerSoundEffectPresenter = PickerSoundEffectPresenter()
    lateinit var binding:ActivityPickerSoundEffectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerSoundEffectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialization of the presenter
        pickerSoundEffectPresenter.onCreate(this, applicationContext)

        buttonNumber = intent.getIntExtra("ButtonNumber", 0)

        binding.backSetting.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        pickerSoundEffectPresenter.onDestroy()
        super.onDestroy()
    }

    override fun loadData(data: ArrayList<String>) {
        binding.recyclerviewFields.layoutManager = GridLayoutManager(this,2)
        binding.recyclerviewFields.adapter = RecycleViewAdapterSoundEffect(
            applicationContext,
            data,
            this
        )
    }

    override fun dataSetting(configData: GeneralSettingData?) {
        if (configData!!.Theme) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        }
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

    override fun loadData(songName: String) {
        val infoToChangeSoundEffect = Bundle()
        infoToChangeSoundEffect.putString("ButtonNumber","$buttonNumber")
        infoToChangeSoundEffect.putString("SoundEffectName", songName)

        val resultIntent = Intent()
        resultIntent.putExtra("packetDataSoundEffect", infoToChangeSoundEffect)
        setResult(RESULT_OK,resultIntent)

        finish()
    }
}