package com.example.champemix.presenter

import android.content.Context

class MainPresenter {

    private var view: View? = null

    interface View{
    }

    fun onCreate(view: View) {
        this.view = view
    }

    fun onDestroy() {
        this.view = null
    }

    fun playerSound(context: Context) {

    }
}