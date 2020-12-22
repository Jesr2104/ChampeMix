package com.example.champemix.presenter

import android.content.Context
import android.widget.Toast
import com.example.champemix.model.ConfigurationData
import com.example.champemix.utility.SoundButton

class SettingButtonPresenter {

    private var view: View? = null
    private var edited: Boolean = false
    private var dataSetting: ArrayList<SoundButton> = arrayListOf()

    interface View{
        fun loadData(data: ArrayList<SoundButton>)
    }

    fun onCreate(view: View, context: Context){
        this.view = view

        // We read the configuration that is saved, if none is found, we will create one with the default configuration
        val data = ConfigurationData().customPreference(context).getList()!!

        // Load the base information
        data.forEach {
            dataSetting.add(it)
        }

        // we send the information to the view to update the data
        view.loadData(data)
    }

    fun edited(){
        edited = true
    }

    fun resetVolume(idButton: Int, newValue: Float){
        dataSetting[idButton].volume = newValue
    }

    fun resetResource(idButton: Int, newValue: String){
        println(newValue)
    }

    fun onDestroy(context: Context) {
        if(edited){
            ConfigurationData().customPreference(context).setList(
                dataSetting)
            Toast.makeText(context, "successfully saved", Toast.LENGTH_SHORT).show()
        }
        this.view = null
    }
}