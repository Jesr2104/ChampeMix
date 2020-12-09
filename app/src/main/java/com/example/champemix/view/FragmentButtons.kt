package com.example.champemix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.champemix.databinding.FragmentButtonsBinding

class FragmentButtons : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentButtonsBinding.inflate(layoutInflater)



        return binding.root
    }
}