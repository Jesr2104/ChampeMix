package com.example.champemix.presenter

import android.content.Context
import android.widget.Toast
import com.example.champemix.model.GeneralSetting
import com.example.champemix.utility.GeneralSettingData
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralSettingPresenter {

    private var view: View? = null
    private var edited: Boolean = false

    //-------------------   Container with new config   ---------------------//
    lateinit var newConfig:GeneralSettingData

    interface View{
        fun loadData(newConfig: GeneralSettingData)
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        val configData = GeneralSetting().customPreference(context).getData()

        if (configData == null){
            GeneralSetting().customPreference(context).setData(GeneralSettingData())
        }

        newConfig = configData!!
        view.loadData(newConfig)
    }

    fun changeSaveKeyboard(newValue: Boolean){
        newConfig.saveKeyboard = newValue
    }

    fun changeChangeTheme(newValue: Boolean){
        newConfig.Theme = newValue
    }

    fun edited(){
        edited = true
    }

    fun onDestroy(context: Context) {
        if(edited){
            GeneralSetting().customPreference(context).setData(newConfig)
            Toast.makeText(context, "successfully saved", Toast.LENGTH_SHORT).show()
        }

        this.view = null
    }
}