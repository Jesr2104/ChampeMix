package com.example.champemix.presenter

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class MyPlayerSound {

    var player: SoundPool? = null

    var attributes: AudioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()

    init {
        player = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(30)
            .build()
    }

    fun playSound(resource: Int, context: Context) {

        val volume = 0.05f
        player!!.load(context, resource, 1)

        player!!.setOnLoadCompleteListener { soundPool, sampleId, _ ->
            soundPool.play(
                sampleId,
                volume,
                volume,
                0,
                0,
                0f
            )
        }
    }
}