package com.example.champemix.presenter

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.champemix.R

class PlayerService : Service() {

    private var player = MediaPlayer()
    private var context = this

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        player = MediaPlayer.create(context, R.raw.ensename_a_olvidar)
        player.isLooping = true
        player.setVolume(0.9f, 0.9f)

        player.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return onBind(intent)
    }
}