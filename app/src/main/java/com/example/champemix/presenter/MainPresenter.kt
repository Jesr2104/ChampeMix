package com.example.champemix.presenter

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.example.champemix.model.ConfigurationData
import com.example.champemix.model.GeneralSetting
import com.example.champemix.presenter.tools.MyPlayerSong
import com.example.champemix.utility.GeneralSettingData

class MainPresenter {

    private var view: View? = null
    lateinit var playerSong: MyPlayerSong

    interface View {
        fun loadDuration(duration: Int)
        fun updateFinish()
    }

    fun onCreate(view: View, context: Context) {
        this.view = view

        // check the general setting
        val configData = GeneralSetting().customPreference(context).getData()

        if (configData == null) {
            GeneralSetting().customPreference(context).setData(GeneralSettingData())
        }

        //==================================================================================
        //      Check the general configurations to know is
        //      the app need to save configuration buttons or not
        //==================================================================================
        if (configData != null){
            if (!configData.saveKeyboard) {
                ConfigurationData().deleteData(context)
            }
        }
            // if the application don't need to save the configurations delete and
            // load the configuration for default
        //==================================================================================
    }

    /**
     *  @functionality: function to put volume down
     */
    fun lowerVolume(context: Context){
        val streamType = AudioManager.STREAM_MUSIC
        val audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioManager.adjustStreamVolume(
            streamType,
            AudioManager.ADJUST_LOWER,
            AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
        )
    }

    /**
     *  @functionality: function to put volume up
     */
    fun raiseVolume(context: Context){
        val streamType = AudioManager.STREAM_MUSIC
        val audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioManager.adjustStreamVolume(
            streamType,
            AudioManager.ADJUST_RAISE,
            AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
        )
    }

    fun playSong(context: Context, song: Int){
        playerSong = MyPlayerSong(context, song)
        view!!.loadDuration(playerSong.duration())
        playerSong.play()
        finish()
    }

    fun finish(){
        playerSong.getPlayer().setOnCompletionListener {
            view!!.updateFinish()
            playerSong.finishSong()
        }
    }

    /**
     *  @functionality: function to set time of the app on minutes and secords
     */
    fun formatDuration(time_milliSeconds: Int): String {

        val minutes = ((time_milliSeconds %  (1000 * 60 * 60)) / (1000 * 60)).toInt()
        val seconds = ((time_milliSeconds %  (1000 * 60 * 60)) % (1000 * 60) / 1000).toInt()

        return if (seconds in 0..9){
            "$minutes:0${seconds}"
        } else{
            "$minutes:${seconds}"
        }
    }

    fun onDestroy() {
        this.view = null
    }
}