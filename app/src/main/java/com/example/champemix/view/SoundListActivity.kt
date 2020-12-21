package com.example.champemix.view

import android.R.raw
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.champemix.R
import com.example.champemix.databinding.ActivitySoundListBinding
import java.lang.reflect.Field


class SoundListActivity : AppCompatActivity() {

    private var buttonNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySoundListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNumber = intent.getIntExtra("ButtonNumber", 0)

        val fields: Array<Field> = R.raw::class.java.fields

        val data = R.raw::class.java.fields

//        data!!.forEach {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//        }
    }

    fun allRawFiles(): List<Int>? {
        val resIds: MutableList<Int> = ArrayList()
        val fields = raw::class.java.fields
        for (field in fields) {
            try {
                resIds.add(field.getInt(field))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return resIds
    }

    fun listRaw() {
        val fields = raw::class.java.fields
        for (count in fields.indices) {
            Toast.makeText(this, "Name: ${fields[count].name}", Toast.LENGTH_SHORT).show()
        }
    }




}