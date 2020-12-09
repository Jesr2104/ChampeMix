package com.example.champemix.presenter

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import com.example.champemix.R

class MainPresenter {

    private var view: View? = null
    private var player = MediaPlayer()

    interface View{
        fun runningSound()
        fun durationSound(totalTime: Int)
        fun currentPosition(currentTime: Int)
        fun nameSound(name: String)
    }

    fun onCreate(view: View) {
        this.view = view
    }

    fun onDestroy() {
        this.view = null
    }

    fun playerSound(context: Context){

        // this is missing to be create inside of service
        var totalTime = 0

        player = MediaPlayer.create(context, R.raw.ensename_a_olvidar)
        player.isLooping = true
        player.setVolume(0.5f, 0.5f)
        totalTime = player.duration

        if (player.isPlaying){
            player.pause()
        } else {
            player.start()
        }
        

        view!!.nameSound("no yet")
        view!!.currentPosition(player.currentPosition)
        view!!.runningSound()
        view!!.durationSound(totalTime)

    }
}