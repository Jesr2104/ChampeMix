package com.just_jump.champemix.model

import com.just_jump.champemix.R

class ResourceSoundRaw {

    private val listSound: ArrayList<String> = arrayListOf()

    fun getMusicListRaw(): ArrayList<String>{
        val fields = R.raw::class.java.fields

        for (item in fields) {
            if (item.name[0] != 's'){
                listSound.add(item.name)
            }
        }
        return listSound
    }

    fun getSoundEffectListRaw(): ArrayList<String> {
        val fields = R.raw::class.java.fields

        for (item in fields) {
            if (item.name[0] != 'm'){
                listSound.add(item.name)
            }
        }
        return listSound
    }
}