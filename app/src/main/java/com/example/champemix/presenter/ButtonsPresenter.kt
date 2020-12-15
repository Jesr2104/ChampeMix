package com.example.champemix.presenter

import android.content.Context
import com.example.champemix.R

class ButtonsPresenter {

    private var view: View? = null

    interface View{
    }

    fun onCreate(view: View) {
        this.view = view
    }

    fun onDestroy() {
        this.view = null
    }

    fun playerSound(context: Context, numberSound: Int) {
        val player = MyPlayerSound()
        when (numberSound){
            1 -> {player.playSound(R.raw.sound_effect_como, context)}
            2 -> {player.playSound(R.raw.sound_effect_allright, context)}
            3 -> {player.playSound(R.raw.sound_effect_dembow, context)}
            4 -> {player.playSound(R.raw.sound_effect_dog, context)}
            5 -> {player.playSound(R.raw.sound_effect_leon, context)}
            6 -> {player.playSound(R.raw.sound_effect_snare6limpio, context)}
            7 -> {player.playSound(R.raw.sound_effect_snare7limpio, context)}
            8 -> {player.playSound(R.raw.sound_effect_sound1, context)}
            9 -> {player.playSound(R.raw.sound_effect_sound3, context)}
            0 -> {player.playSound(R.raw.sound_effect_sound6, context)}
        }



    }
}