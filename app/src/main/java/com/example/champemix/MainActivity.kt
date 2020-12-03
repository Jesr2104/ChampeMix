package com.example.champemix

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.champemix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var settingOn = false

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        binding.menuButton.setOnClickListener{
            val menuConfig = Intent(this, ConfigActivity::class.java)
            startActivity(menuConfig)

        }

        val fragmentButtons = FragmentButtons()
        makeCurrentFragment(fragmentButtons)

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
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.LayoutFragmentButtons, fragment)
            commit()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
}