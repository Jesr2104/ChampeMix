package com.example.champemix.presenter

import android.content.Context
import com.example.champemix.model.DataSounds
import com.example.champemix.utility.DefaultConfiguration
import com.example.champemix.utility.SoundButton

class ButtonsPresenter {

    private var view: View? = null
    private var soundsResources: ArrayList<String> = arrayListOf()

    interface View{
        fun loadData(data: ArrayList<SoundButton>)
    }

    fun onCreate(view: View,context: Context) {
        this.view = view

        // We read the configuration that is saved, if none is found, we will create one with the default configuration
        var data = DataSounds().customPreference(context).getList()

        while (data == null){
            DefaultConfiguration(context)
            data = DataSounds().customPreference(context).getList()!!
        }

        // load the sounds to be ready to player
        data.forEach{
            soundsResources.add(it.resource)
        }

        // we send the information to the view to update the data
        view.loadData(data)
    }

    fun onDestroy() {
        this.view = null
    }

    fun playerSound(context: Context, numberSound: Int) {

        val player = MyPlayerSound()
        val idResource = context.resources.getIdentifier(soundsResources[numberSound], "raw", context.packageName)

        when (numberSound){
            0 -> {player.playSound(idResource, context)}
            1 -> {player.playSound(idResource, context)}
            2 -> {player.playSound(idResource, context)}
            3 -> {player.playSound(idResource, context)}
            4 -> {player.playSound(idResource, context)}
            5 -> {player.playSound(idResource, context)}
            6 -> {player.playSound(idResource, context)}
            7 -> {player.playSound(idResource, context)}
            8 -> {player.playSound(idResource, context)}
            9 -> {player.playSound(idResource, context)}
        }
    }
}