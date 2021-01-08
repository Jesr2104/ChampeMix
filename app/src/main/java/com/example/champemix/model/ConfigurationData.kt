package com.example.champemix.model

import android.content.Context
import android.content.SharedPreferences
import com.example.champemix.utility.SoundButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConfigurationData {
    private val gSonInstance = Gson()
    private var dataSharePreference: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): ConfigurationData {
        if (dataSharePreference == null)
            dataSharePreference = context.getSharedPreferences(
                "dataSoundsConfig",
                Context.MODE_PRIVATE
            )
        return this
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun getList(): ArrayList<SoundButton>? {
        val fooType: Type = object : TypeToken<ArrayList<SoundButton>>() {}.type
        if (dataSharePreference?.contains("dataSoundsConfig")!!) {
            val json = dataSharePreference?.getString("dataSoundsConfig", "DEFAULT")
            return gSonInstance.fromJson(json, fooType)
        }
        return null
    }

    fun setList(newData: ArrayList<SoundButton>) {
        val fooType: Type = object : TypeToken<ArrayList<SoundButton>>() {}.type
        val json = gSonInstance.toJson(newData,fooType)
        dataSharePreference?.editMe {
            it.putString("dataSoundsConfig", json)
        }
    }

    fun deleteData(context: Context){
        val shareP = customPreference(context).dataSharePreference!!.edit().clear()
        shareP.apply()
    }
}