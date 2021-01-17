package com.example.champemix.presenter

import android.content.Context
import com.example.champemix.model.GeneralSetting
import com.example.champemix.model.ResourceSoundRaw
import com.example.champemix.utility.GeneralSettingData

class PickerSoundEffectPresenter {

    private var view: View? = null

    interface View{
        fun loadData(dataList: ArrayList<String>)
        fun dataSetting(configData: GeneralSettingData?)
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        val data = ResourceSoundRaw().getSoundEffectListRaw()
        view.loadData(data)

        // check the general setting
        val configData = GeneralSetting().customPreference(context).getData()

        // send the information on the interface to load the theme
        view.dataSetting(configData)
    }

    fun onDestroy() {
        this.view = null
    }
}