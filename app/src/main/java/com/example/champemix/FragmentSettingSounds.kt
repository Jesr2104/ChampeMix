package com.example.champemix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentSettingSounds : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewFragment = inflater.inflate(R.layout.fragment_setting_sounds, container, false)

        return viewFragment
    }
}