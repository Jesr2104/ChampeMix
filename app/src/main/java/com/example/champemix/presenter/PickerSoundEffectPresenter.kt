package com.example.champemix.presenter

import com.example.champemix.model.ResourceSoundRaw

class PickerSoundEffectPresenter {

    private var view: View? = null

    interface View{
        fun loadData(dataList: ArrayList<String>)
    }

    fun onCreate(view: View) {
        this.view = view

        val data = ResourceSoundRaw().getSoundEffectListRaw()
        view.loadData(data)
    }

    fun onDestroy() {
        this.view = null
    }
}