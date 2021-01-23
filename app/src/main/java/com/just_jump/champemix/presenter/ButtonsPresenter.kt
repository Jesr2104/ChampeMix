package com.just_jump.champemix.presenter

import android.content.Context
import com.just_jump.champemix.model.ConfigurationData
import com.just_jump.champemix.presenter.tools.MyPlayerSound
import com.just_jump.champemix.utility.DefaultConfiguration
import com.just_jump.champemix.utility.SoundButton

class ButtonsPresenter {

    private var view: View? = null
    private var soundsResources: ArrayList<String> = arrayListOf()
    private var soundsVolume: ArrayList<Float> = arrayListOf()

    interface View{
        fun loadData(data: ArrayList<SoundButton>)
    }

    fun onCreate(view: View,context: Context) {
        this.view = view

        // We read the configuration that is saved, if none is found, we will create one with the default configuration
        var data = ConfigurationData().customPreference(context).getList()

        while (data == null){
            DefaultConfiguration(context)
            data = ConfigurationData().customPreference(context).getList()!!
        }

        // load the sounds to be ready to player
        data.forEach{
            soundsResources.add(it.resource)
            soundsVolume.add(it.volume)
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
            0 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            1 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            2 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            3 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            4 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            5 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            6 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            7 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            8 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
            9 -> {player.playSound(idResource, context, soundsVolume[numberSound])}
        }
    }
}