package com.example.champemix.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.champemix.R
import com.example.champemix.databinding.ActivityMainBinding
import com.example.champemix.model.ConfigurationData
import com.example.champemix.model.GeneralSetting
import com.example.champemix.presenter.MainPresenter
import com.example.champemix.utility.GeneralSettingData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), MainPresenter.View {

    var settingOn = false
    var generalSetting = false
    private val mainPresenter = MainPresenter()
    lateinit var binding: ActivityMainBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // block the app orientation of the activity on Landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // initialization of the presenter
        mainPresenter.onCreate(this, applicationContext)

        //==================================================================================
        //      Check the general configurations to know is
        //      the app need to save configuration buttons
        //==================================================================================
        val configData = GeneralSetting().customPreference(applicationContext).getData()
        if (!configData!!.saveKeyboard){
            ConfigurationData().deleteData(applicationContext)
        }
        //==================================================================================

        // fragment initial, we need to show the buttons like a first option
        val fragmentButtons = FragmentButtons()
        makeCurrentFragment(fragmentButtons)

        updateVolume()

        //==================================================================================
        //     Event control
        //==================================================================================

        binding.settingButton.setOnClickListener {
            generalSetting = false
            settingOn = if (settingOn) {
                makeCurrentFragment(FragmentButtons())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune,theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings,theme)
                false
            } else {
                makeCurrentFragment(FragmentSettingSounds())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_keyboard,theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings,theme)
                true
            }
        }

        binding.generalSetting.setOnClickListener {
            settingOn = false
            generalSetting = if (!generalSetting){
                makeCurrentFragment(FragmentGeneralSettings())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune,theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_back,theme)
                true
            } else {
                makeCurrentFragment(FragmentButtons())
                binding.settingButton.icon = resources.getDrawable(R.drawable.ic_tune,theme)
                binding.generalSetting.icon = resources.getDrawable(R.drawable.ic_settings,theme)
                false
            }
        }

        binding.volumeUp.setOnClickListener {

            val streamType = AudioManager.STREAM_MUSIC
            val audioManager =
                applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager

            binding.volumeUp.iconTint = getColorStateList(R.color.color5)

            Timer().schedule(100) {
                binding.volumeUp.iconTint = getColorStateList(R.color.main_controls)
            }

            audioManager.adjustStreamVolume(
                streamType,
                AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
            )

            updateVolume()
        }

        binding.volumeDown.setOnClickListener {

            val streamType = AudioManager.STREAM_MUSIC
            val audioManager =
                applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager

            binding.volumeDown.iconTint = getColorStateList(R.color.color5)

            Timer().schedule(100) {
                binding.volumeDown.iconTint = getColorStateList(R.color.main_controls)
            }

            audioManager.adjustStreamVolume(
                streamType,
                AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
            )
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

        //==================================================================================
    }

    // to update the current volume on the app
    @SuppressLint("SetTextI18n")
    private fun updateVolume() {
        val audioManager =
            applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val volume = volumeLevel.toFloat() / maxVolume

        binding.valueVolume.text = "${(volume * 100).toInt()}%"
    }

    override fun dataSetting(configData: GeneralSettingData?) {
        if (configData!!.Theme){
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        }
    }

    // function to change between Fragments
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.LayoutFragmentButtons, fragment)
            commit()
        }
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    // function to hide the navigationBar and statusBar and leave float
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}