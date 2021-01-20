package com.example.champemix.presenter.tools

import android.content.Context
import android.media.MediaPlayer

class MyPlayerSong(context: Context, song: Int) {

    private var mPlayer = MediaPlayer()
    private var position = 0

    init {
        mPlayer = MediaPlayer.create(context, song)
    }

    fun play() {
        mPlayer.start()
    }

    fun setNewSong(context: Context, idNewSong: Int){
        if (mPlayer.isPlaying) {
            mPlayer.stop()
            mPlayer.release()
            mPlayer = MediaPlayer.create(context, idNewSong)
        }
        mPlayer.start()
    }

    fun getPlayer(): MediaPlayer {
       return mPlayer
    }

    fun currentPosition(): Int {
        return mPlayer.currentPosition
    }

    fun duration(): Int {
        return mPlayer.duration
    }

    fun pause(){
        if (mPlayer.isPlaying){
            position = currentPosition()
            mPlayer.pause()
        }
    }

    fun stop(){
        mPlayer.stop()
        mPlayer.release()
    }

    fun finishSong(){
        position = 0
        mPlayer.stop()
    }

    fun isPlayer(): Boolean {
        return mPlayer.isPlaying
    }

    fun resume(){
        mPlayer.seekTo(position)
        play()
    }

    fun setNewPositionPlayer(newPosition: Int){
        position = newPosition
        mPlayer.seekTo(newPosition)
    }

    fun forward(){
        position = currentPosition()

        if (position + 5000 > duration()){
            position = duration()
        } else {
            position += 5000
        }

        mPlayer.seekTo(position)
        play()
    }

    fun rewind(){
        position = currentPosition()

        if (position - 5000 < 0){
            position = 0
        } else {
            position -= 5000
        }

        mPlayer.seekTo(position)
        play()
    }

    fun onDestroy() {
        mPlayer.reset()
        mPlayer.release()
    }
}