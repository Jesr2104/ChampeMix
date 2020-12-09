package com.example.champemix.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.champemix.R
import com.example.champemix.databinding.ActivityMainBinding
import com.example.champemix.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val mainPresenter = MainPresenter()
    var settingOn = false
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // block the app orientation of the activity on Landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // initialization of the presenter
        mainPresenter.onCreate(this)

        // fragment initial, we need to show the buttons like a first option
        val fragmentButtons = FragmentButtons()
        makeCurrentFragment(fragmentButtons)

        //==================================================================================
        //     Event control
        //==================================================================================

        binding.settingButton.setOnClickListener{
            settingOn = if (settingOn){
                val fragmentButtons = FragmentButtons()
                makeCurrentFragment(fragmentButtons)
                false
            } else {
                val fragmentSettingSounds = FragmentSettingSounds()
                makeCurrentFragment(fragmentSettingSounds)
                true
            }
        }

        binding.buttonPlay.setOnClickListener{
            mainPresenter.playerSound(this)
        }

        //==================================================================================
    }

    override fun runningSound() {
        binding.buttonPlay.setImageResource(R.drawable.ic_pause)
    }

    override fun durationSound(totalTime: Int) {
        var calDuration = "${totalTime / 1000 / 60}:${totalTime / 1000 % 60}"
        binding.durationSound.text = calDuration
    }

    override fun currentPosition(currentTime: Int) {
        var currentPosition = "${currentTime / 1000 / 60}:${currentTime / 1000 % 60}"
        binding.durationOnTime.text = currentPosition
    }

    override fun nameSound(name: String) {
        binding.nameSound.text = name
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    // function to change between Fragments
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.LayoutFragmentButtons, fragment)
            commit()
        }
    }

    // function to hide the navegationBar and statusBar and leave float
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