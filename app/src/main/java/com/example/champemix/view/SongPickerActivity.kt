package com.example.champemix.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.champemix.databinding.ActivitySongPickerBinding

class SongPickerActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backSetting.setOnClickListener {
            finish()
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
}