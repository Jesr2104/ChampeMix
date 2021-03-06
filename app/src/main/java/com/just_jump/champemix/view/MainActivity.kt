package com.just_jump.champemix.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.just_jump.champemix.R
import com.just_jump.champemix.databinding.ActivityMainBinding
import com.just_jump.champemix.model.GeneralSetting
import com.just_jump.champemix.presenter.MainPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(), MainPresenter.View {

    private var settingOn = false
    private var state = false
    private var generalSetting = false
    private val mainPresenter = MainPresenter()
    private val handle: Handler = Handler()
    private var idSong: Int = -1
    lateinit var binding: ActivityMainBinding

    // update in real time for current duration and seek bar
    private val updateAction = object : Runnable {
        override fun run() {
            updateSeekBar()
            binding.currentPosition.text =
                mainPresenter.formatDuration(mainPresenter.playerSong!!.currentPosition())

            handle.postDelayed(this, 1000)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        // check theme of the application and set the previous one
        checkTheme(applicationContext)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialization of the presenter
        mainPresenter.onCreate(this, applicationContext)

        // fragment initial, we need to show the buttons like a first option
        val fragmentButtons = FragmentButtons()
        makeCurrentFragment(fragmentButtons)

        // setup the volume when the app start
        updateVolume()

        //==================================================================================
        //     Event control
        //==================================================================================

        binding.play.setOnClickListener {

            binding.play.iconTint = getColorStateList(R.color.color7)

            Timer().schedule(200) {
                binding.play.iconTint = getColorStateList(R.color.main_controls)
            }

            if (!state) {
                if (idSong > 0){
                    mainPresenter.playSong(applicationContext, idSong)
                    binding.play.icon = resources.getDrawable(R.drawable.ic_pause, theme)
                    updateCurrentPosition()

                    // this property gonna change when you change the song
                    state = true
                } else {
                    Toast.makeText(this, getString(R.string.ms_select_song), Toast.LENGTH_SHORT).show()
                }
            } else {
                if (mainPresenter.playerSong!!.isPlaying()) {
                    mainPresenter.playerSong!!.pause()
                    handle.removeCallbacks(updateAction)
                    binding.play.icon = resources.getDrawable(R.drawable.ic_play, theme)
                } else {
                    mainPresenter.playerSong!!.resume()
                    updateCurrentPosition()
                    binding.play.icon = resources.getDrawable(R.drawable.ic_pause, theme)
                }
            }
        }

        binding.fastForward.setOnClickListener {

            binding.fastForward.iconTint = getColorStateList(R.color.color7)

            Timer().schedule(200) {
                binding.fastForward.iconTint = getColorStateList(R.color.main_controls)
            }

            if (state) {
                mainPresenter.playerSong!!.forward()
                binding.play.icon = resources.getDrawable(R.drawable.ic_pause, theme)
            }
        }

        binding.fastRewind.setOnClickListener {

            binding.fastRewind.iconTint = getColorStateList(R.color.color7)

            Timer().schedule(200) {
                binding.fastRewind.iconTint = getColorStateList(R.color.main_controls)
            }

            if (state) {
                mainPresenter.playerSong!!.rewind()
                binding.play.icon = resources.getDrawable(R.drawable.ic_pause, theme)
            }
        }

        binding.settingButton.setOnClickListener {
            generalSetting = false
            settingOn = if (settingOn) {
                makeCurrentFragment(FragmentButtons())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune, theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings, theme)
                false
            } else {
                makeCurrentFragment(FragmentSettingSounds())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_keyboard, theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings, theme)
                true
            }
        }

        binding.generalSetting.setOnClickListener {
            settingOn = false
            generalSetting = if (!generalSetting) {
                makeCurrentFragment(FragmentGeneralSettings())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune, theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_back, theme)
                true
            } else {
                makeCurrentFragment(FragmentButtons())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune, theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings, theme)
                false
            }
        }

        binding.volumeUp.setOnClickListener {

            binding.volumeUp.iconTint = getColorStateList(R.color.color7)

            mainPresenter.raiseVolume(applicationContext)

            Timer().schedule(200) {
                binding.volumeUp.iconTint = getColorStateList(R.color.main_controls)
            }

            updateVolume()
        }

        binding.volumeDown.setOnClickListener {
            binding.volumeDown.iconTint = getColorStateList(R.color.color7)

            mainPresenter.lowerVolume(applicationContext)

            Timer().schedule(200) {
                binding.volumeDown.iconTint = getColorStateList(R.color.main_controls)
            }

            updateVolume()
        }

        binding.buttonExit.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Please Confirm")
                .setMessage("Do you want to exit")
                .setNegativeButton("No") { _, _ ->
                    // don't do nothing
                }
                .setPositiveButton("Yes") { _, _ ->
                    finish()
                }
                .show()
        }

        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (state) {
                    if (fromUser) {
                        val resultNewPosition =
                            (progress.toFloat() / 100) * mainPresenter.playerSong!!.duration()
                        mainPresenter.playerSong!!.setNewPositionPlayer(resultNewPosition.toInt())

                        //update the current time when the user move the seek bar
                        binding.currentPosition.text =
                            mainPresenter.formatDuration(mainPresenter.playerSong!!.currentPosition())
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.songName.setOnClickListener {

            binding.songName.setTextColor(getColorStateList(R.color.color3))

            val songPicker = Intent(this, SongPickerActivity::class.java)
            startActivityForResult(songPicker, 1)

            Timer().schedule(100) {
                binding.songName.setTextColor(getColorStateList(R.color.white))
            }
        }

        //==================================================================================
    }

    // function to load theme of the application
    private fun checkTheme(context: Context) {
        // check the general setting
        val configData = GeneralSetting().customPreference(context).getData()

        if(configData != null) {
            if (configData.Theme) {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            } else {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            }
        }
    }

    // function to update the current volume on the app
    @SuppressLint("SetTextI18n")
    private fun updateVolume() {
        val audioManager =
            applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val volume = volumeLevel.toFloat() / maxVolume

        binding.valueVolume.text = "${(volume * 100).toInt()}%"
    }

    private fun updateSeekBar(){
        binding.seekBar.progress =
            ((mainPresenter.playerSong!!.currentPosition().toFloat() / mainPresenter.playerSong!!.duration()) * 100).toInt()
    }

    private fun updateCurrentPosition() {
        if (mainPresenter.playerSong!!.isPlaying()) {
            handle.postDelayed(updateAction, 1000)
        }
    }

    override fun loadDuration(duration: Int) {
        binding.durationSong.text =
            mainPresenter.formatDuration(mainPresenter.playerSong!!.duration())
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun updateFinish() {
        state = false
        handle.removeCallbacks(updateAction)
        binding.seekBar.progress = 0
        binding.play.icon = resources.getDrawable(R.drawable.ic_play, theme)
        binding.currentPosition.text = "0:00"
    }

    // function to change between Fragments
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.LayoutFragmentButtons, fragment)
            commit()
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val resultTemp = data!!.getStringExtra("packetDataSong")
                if (resultTemp != null){
                    // to delete the _ on the name of the name file
                    binding.songName.text = resultTemp.replace('_',' ')

                    if (state){
                        handle.removeCallbacks(updateAction)
                        mainPresenter.playerSong!!.stop()
                        state = false
                        binding.play.icon = resources.getDrawable(R.drawable.ic_play, theme)
                        binding.seekBar.progress = 0
                    }

                    idSong = applicationContext.resources.getIdentifier(
                        resultTemp,
                        "raw",
                        applicationContext!!.packageName
                    )
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}