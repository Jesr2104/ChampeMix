package com.example.champemix.utility

import android.content.Context
import com.example.champemix.R
import com.example.champemix.model.ConfigurationData

class DefaultConfiguration(context: Context) {
    init {
        val listButtons:ArrayList<SoundButton> = arrayListOf()
        var i = 0
        val soundsResource: Array<Int> = arrayOf(
            R.raw.sound_effect_snare7limpio,
            R.raw.sound_effect_sound3,
            R.raw.sound_effect_dembow,
            R.raw.sound_effect_dog,
            R.raw.sound_effect_sound6,
            R.raw.sound_effect_snare6limpio,
            R.raw.sound_effect_como,
            R.raw.sound_effect_sound1,
            R.raw.sound_effect_allright,
            R.raw.sound_effect_leon
        )
        while (i < 10){

            val soundButton = SoundButton()
            val nameResource = context.resources.getResourceEntryName(soundsResource[i])

            soundButton.titleSound = GetMetadata().getTitle(context,soundsResource[i])
            soundButton.duration = GetMetadata().getDuration(context,soundsResource[i])
            soundButton.volume = 0.75f
            soundButton.resource = nameResource

            listButtons.add(soundButton)
            i++
        }
        ConfigurationData().customPreference(context).setList(listButtons)
    }
}



