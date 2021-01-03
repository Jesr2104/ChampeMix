package com.example.champemix.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.champemix.databinding.FragmentPickerSoundEffectBinding
import com.example.champemix.presenter.PickerSoundEffectPresenter
import com.example.champemix.presenter.adapter.RecycleViewAdapterSoundEffect

class FragmentPickerSoundEffect : Fragment(), PickerSoundEffectPresenter.View {

    private val pickerSoundEffectPresenter = PickerSoundEffectPresenter()
    private lateinit var binding: FragmentPickerSoundEffectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentPickerSoundEffectBinding.inflate(layoutInflater)

        // initialization of the presenter
        pickerSoundEffectPresenter.onCreate(this)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        pickerSoundEffectPresenter.onDestroy()
        super.onDestroy()
    }

    override fun loadData(dataList: ArrayList<String>) {
        binding.recyclerviewFields.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewFields.adapter = RecycleViewAdapterSoundEffect(
            requireContext(),
            dataList
        )
    }
}