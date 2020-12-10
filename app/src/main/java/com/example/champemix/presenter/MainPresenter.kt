package com.example.champemix.presenter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer

class MainPresenter {

    private var view: View? = null
    private var start = true


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

        val intent = Intent(context, PlayerService::class.java)

        start =
            if (start){
                context.startService(intent)
                false
            } else {
                context.stopService(intent)
                true
            }



        // this is missing to be create inside of service
//        var totalTime = 0

//        player = MediaPlayer.create(context, R.raw.ensename_a_olvidar)
//        player.isLooping = true
//        player.setVolume(0.9f, 0.9f)
//        totalTime = player.duration

//        if (player.isPlaying){
//            player.pause()
//        } else {
//            player.start()
//        }
        

//        view!!.nameSound("Enseñame a olvidarte - ")
//        view!!.currentPosition(player.currentPosition)
//        view!!.runningSound()
//        view!!.durationSound(totalTime)

    }
}