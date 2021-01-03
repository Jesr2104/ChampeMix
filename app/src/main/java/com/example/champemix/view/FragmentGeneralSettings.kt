package com.example.champemix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.champemix.databinding.FragmentGeneralSettingsBinding

class FragmentGeneralSettings : Fragment() {

    private lateinit var binding: FragmentGeneralSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentGeneralSettingsBinding.inflate(layoutInflater)

        return binding.root
    }
}