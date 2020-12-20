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
            .setMaxStreams(60)
            .build()
    }

    fun playSound(resource: Int, context: Context) {

        val volume = 1.0f
        player!!.load(context, resource, 1)

        player!!.setOnLoadCompleteListener { soundPool, sampleId, _ ->
            soundPool.play(
                sampleId,
                volume,
                volume,
                1,
                0,
                1f
            )
        }
    }
}