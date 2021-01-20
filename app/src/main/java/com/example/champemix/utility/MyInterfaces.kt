package com.example.champemix.utility

interface LoadEffectSound {
    fun recycleViewControlEventSound(songName: String)
}

interface LoadSong {
    fun recycleViewControlEventSong(songName: String)
}

interface PlayerSample{
    fun playSong(idResource: Int)
}

interface UpdateIcon{
    fun changeIcon()
}