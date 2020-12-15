package com.example.champemix.presenter

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.champemix.R

class PlayerService : Service() {

    private var player = MediaPlayer()
    private val resource = R.raw.music_ensename_a_olvidar

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // start to play the sounds
        playSound(resource)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {

        // stop the sound when the app  is destroyed
        stopSound()

        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return onBind(intent)
    }

    //==================================================================================
    //     My functions
    //==================================================================================

    fun playSound(resource: Int) {
        player = MediaPlayer.create(applicationContext, resource)
        player.start()
    }

    fun stopSound() {
        player.stop()
    }

    fun pauseSound(){
        player.pause()
    }

    fun getDuration(): Int {
        return player.duration
    }

    fun setVolume(Volume: Float) {
        player.setVolume(Volume, Volume)
    }

    fun setRightVolume(rightVolume: Float) {
        //player.setVolume("por definir", rightVolume)
    }

    fun setLeftVolume(leftVolume: Float) {
        //player.setVolume(leftVolume, "por definir")
    }

    fun setLooping(active: Boolean){
        player.isLooping = active
    }


}