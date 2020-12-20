package com.example.champemix.presenter

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
}