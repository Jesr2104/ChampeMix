package com.just_jump.champemix.presenter

import android.content.Context
import com.just_jump.champemix.model.ResourceSoundRaw

class SoundEffectPickerPresenter {

    private var view: View? = null

    interface View{
        fun loadData(dataList: ArrayList<String>)
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        val data = ResourceSoundRaw().getSoundEffectListRaw()
        view.loadData(data)
    }

    fun onDestroy() {
        this.view = null
    }
}