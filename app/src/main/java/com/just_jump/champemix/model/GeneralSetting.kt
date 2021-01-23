package com.just_jump.champemix.model

import android.content.Context
import android.content.SharedPreferences
import com.just_jump.champemix.utility.GeneralSettingData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GeneralSetting {
    private val gSonInstance = Gson()
    private var dataSharePreference: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): GeneralSetting {
        if (dataSharePreference == null)
            dataSharePreference = context.getSharedPreferences(
                "GeneralSetting",
                Context.MODE_PRIVATE
            )
        return this
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun setData(newData: GeneralSettingData) {
        val fooType: Type = object : TypeToken<GeneralSettingData>() {}.type
        val json = gSonInstance.toJson(newData,fooType)
        dataSharePreference?.editMe {
            it.putString("GeneralSetting", json)
        }
    }

    fun getData(): GeneralSettingData? {
        val fooType: Type = object : TypeToken<GeneralSettingData>() {}.type
        if (dataSharePreference?.contains("GeneralSetting")!!) {
            val json = dataSharePreference?.getString("GeneralSetting", "DEFAULT")
            return gSonInstance.fromJson(json, fooType)
        }
        return null
    }
}