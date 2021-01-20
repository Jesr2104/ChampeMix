package com.example.champemix.presenter

import android.content.Context
import com.example.champemix.model.ResourceSoundRaw

class SongPickerPresenter {

    private var view: View? = null

    interface View{
        fun loadData(dataList: ArrayList<String>)
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        val data = ResourceSoundRaw().getMusicListRaw()
        view.loadData(data)
    }

    fun onDestroy() {
        this.view = null
    }
}