package com.example.champemix.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.champemix.databinding.ActivityPickerSoundEffectBinding
import com.example.champemix.presenter.PickerSoundEffectPresenter
import com.example.champemix.presenter.adapter.RecycleViewAdapterSoundEffect

class PickerSoundEffectActivity : AppCompatActivity(), PickerSoundEffectPresenter.View {

    private var buttonNumber: Int = 0
    private val pickerSoundEffectPresenter = PickerSoundEffectPresenter()
    lateinit var binding:ActivityPickerSoundEffectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerSoundEffectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialization of the presenter
        pickerSoundEffectPresenter.onCreate(this)

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
            data
        )
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