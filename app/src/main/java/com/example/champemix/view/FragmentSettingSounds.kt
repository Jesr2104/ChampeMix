package com.example.champemix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.champemix.R

class FragmentSettingSounds : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewFragment = inflater.inflate(R.layout.fragment_setting_sounds, container, false)

        return viewFragment
    }
}

