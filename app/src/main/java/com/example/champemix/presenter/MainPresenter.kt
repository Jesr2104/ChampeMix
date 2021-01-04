package com.example.champemix.presenter

import android.content.Context
import com.example.champemix.model.GeneralSetting
import com.example.champemix.utility.GeneralSettingData

class MainPresenter {

    private var view: View? = null

    interface View{
        fun dataSetting(configData: GeneralSettingData?)
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        val configData = GeneralSetting().customPreference(context).getData()

        if (configData == null){
            GeneralSetting().customPreference(context).setData(GeneralSettingData())
        }
        view.dataSetting(configData)
    }

    fun onDestroy() {
        this.view = null
    }
}