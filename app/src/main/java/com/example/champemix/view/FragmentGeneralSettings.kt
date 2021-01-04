package com.example.champemix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.champemix.databinding.FragmentGeneralSettingsBinding
import com.example.champemix.presenter.GeneralSettingPresenter
import com.example.champemix.utility.GeneralSettingData

class FragmentGeneralSettings : Fragment(), GeneralSettingPresenter.View{

    private val generalSettingPresenter = GeneralSettingPresenter()
    private lateinit var binding: FragmentGeneralSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        binding = FragmentGeneralSettingsBinding.inflate(layoutInflater)

        // initialization of the presenter
        generalSettingPresenter.onCreate(this, activity!!.applicationContext)

        binding.switchMaterial1.setOnCheckedChangeListener { _, isChecked ->
            generalSettingPresenter.edited()
            generalSettingPresenter.changeSaveKeyboard(isChecked)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            generalSettingPresenter.edited()
            val textRadioButton = binding.radioGroup.findViewById<RadioButton>(checkedId).text

            if (textRadioButton == "Light Theme") {
                generalSettingPresenter.changeChangeTheme(true)

            } else {
                generalSettingPresenter.changeChangeTheme(false)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        generalSettingPresenter.onDestroy(context!!)
        super.onDestroy()
    }

    override fun loadData(newConfig: GeneralSettingData) {
        binding.switchMaterial1.isChecked = newConfig.saveKeyboard

        if(newConfig.Theme){
            binding.lightTheme.isChecked = true
        } else {
            binding.darkTheme.isChecked = true
        }
    }
}