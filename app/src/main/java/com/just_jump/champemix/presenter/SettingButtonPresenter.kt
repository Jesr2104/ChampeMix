package com.just_jump.champemix.presenter

import android.content.Context
import android.widget.Toast
import com.just_jump.champemix.model.ConfigurationData
import com.just_jump.champemix.utility.GetMetadata
import com.just_jump.champemix.utility.SoundButton

class SettingButtonPresenter {

    private var view: View? = null
    private var context: Context? = null
    private var edited: Boolean = false
    private var dataSetting: ArrayList<SoundButton> = arrayListOf()

    interface View{
        fun loadData(data: ArrayList<SoundButton>)
    }

    fun onCreate(view: View, context: Context){
        this.view = view
        this.context = context

        // We read the configuration that is saved, if none is found, we will create one with the default configuration
        val data = ConfigurationData().customPreference(context).getList()!!

        // Load the base information
        data.forEach {
            dataSetting.add(it)
        }

        // we send the information to the view to update the data
        view.loadData(data)
    }

    fun edited(){
        edited = true
    }

    fun resetVolume(idButton: Int, newValue: Float){
        dataSetting[idButton].volume = newValue
    }

    fun resetResource(idButton: String, newValue: String){
        dataSetting[idButton.toInt() - 1].resource = newValue
        val idResource = context!!.resources.getIdentifier(newValue, "raw", context!!.packageName)
        dataSetting[idButton.toInt() - 1].titleSound = GetMetadata().getTitle(context!!,idResource)
    }

    fun onDestroy(context: Context) {
        if(edited){
            ConfigurationData().customPreference(context).setList(
                dataSetting)
            Toast.makeText(context, "successfully saved", Toast.LENGTH_SHORT).show()
        }
        this.view = null
    }
}