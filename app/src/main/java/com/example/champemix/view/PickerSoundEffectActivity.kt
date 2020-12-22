package com.example.champemix.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

    override fun onDestroy() {
        pickerSoundEffectPresenter.onDestroy()
        super.onDestroy()
    }

    override fun loadData(data: ArrayList<String>) {
        binding.recyclerviewFields.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewFields.adapter = RecycleViewAdapterSoundEffect(
            applicationContext,
            data
        )
    }
}