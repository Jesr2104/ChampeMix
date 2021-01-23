package com.just_jump.champemix.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.just_jump.champemix.databinding.FragmentSettingSoundsBinding
import com.just_jump.champemix.presenter.SettingButtonPresenter
import com.just_jump.champemix.utility.GetMetadata
import com.just_jump.champemix.utility.SoundButton

class FragmentSettingSounds : Fragment(), SettingButtonPresenter.View {

    private val settingButtonPresenter = SettingButtonPresenter()
    private lateinit var binding: FragmentSettingSoundsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSettingSoundsBinding.inflate(layoutInflater)

        // initialization of the presenter
        settingButtonPresenter.onCreate(this, context!!.applicationContext)

        binding.seekBarVolButton1.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton1.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(0, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton2.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton2.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(1, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton3.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton3.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(2, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton4.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton4.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(3, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton5.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton5.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(4, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton6.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton6.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(5, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton7.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton7.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(6, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton8.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton8.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(7, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton9.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton9.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(8, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.seekBarVolButton10.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                settingButtonPresenter.edited()
                binding.volButton10.text = "${progress}%"

                // we send to the presenter the new information to update
                settingButtonPresenter.resetVolume(9, ((progress).toFloat() / 100))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.insertSongButton.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 1)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton2.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 2)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton3.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 3)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton4.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 4)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton5.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 5)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton6.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 6)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton7.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 7)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton8.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 8)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton9.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 9)
            startActivityForResult(soundList,1)
        }

        binding.insertSoundButton10.setOnClickListener {
            val soundList = Intent(context, SoundEffectPickerActivity::class.java)
            soundList.putExtra("ButtonNumber", 10)
            startActivityForResult(soundList,1)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val resultTemp = data!!.getBundleExtra("packetDataSoundEffect")
                val soundNumberButton = resultTemp!!.get("ButtonNumber").toString()
                val soundName = resultTemp.get("SoundEffectName").toString()

                val idResource = context!!.resources.getIdentifier(soundName, "raw", context!!.packageName)

                when(soundNumberButton){
                    "1"->{binding.sampleNameButton1.text = GetMetadata().getTitle(context!!,idResource)}
                    "2"->{binding.sampleNameButton2.text = GetMetadata().getTitle(context!!,idResource)}
                    "3"->{binding.sampleNameButton3.text = GetMetadata().getTitle(context!!,idResource)}
                    "4"->{binding.sampleNameButton4.text = GetMetadata().getTitle(context!!,idResource)}
                    "5"->{binding.sampleNameButton5.text = GetMetadata().getTitle(context!!,idResource)}
                    "6"->{binding.sampleNameButton6.text = GetMetadata().getTitle(context!!,idResource)}
                    "7"->{binding.sampleNameButton7.text = GetMetadata().getTitle(context!!,idResource)}
                    "8"->{binding.sampleNameButton8.text = GetMetadata().getTitle(context!!,idResource)}
                    "9"->{binding.sampleNameButton9.text = GetMetadata().getTitle(context!!,idResource)}
                    "10"->{binding.sampleNameButton10.text = GetMetadata().getTitle(context!!,idResource)}
                }

                settingButtonPresenter.resetResource(soundNumberButton, soundName)
                settingButtonPresenter.edited()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        settingButtonPresenter.onDestroy(context!!)
        super.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    override fun loadData(data: ArrayList<SoundButton>) {

        var i = 1
        for (item in data) {

            val title = item.titleSound
            val volume = item.volume

            when(i){
                1 -> {
                    binding.sampleNameButton1.text = title
                    binding.seekBarVolButton1.progress = (volume * 100).toInt()
                    binding.volButton1.text = "${(volume * 100).toInt()}%"
                }
                2 -> {
                    binding.sampleNameButton2.text = title
                    binding.seekBarVolButton2.progress = (volume * 100).toInt()
                    binding.volButton2.text = "${(volume * 100).toInt()}%"
                }
                3 -> {
                    binding.sampleNameButton3.text = title
                    binding.seekBarVolButton3.progress = (volume * 100).toInt()
                    binding.volButton3.text = "${(volume * 100).toInt()}%"
                }
                4 -> {
                    binding.sampleNameButton4.text = title
                    binding.seekBarVolButton4.progress = (volume * 100).toInt()
                    binding.volButton4.text = "${(volume * 100).toInt()}%"
                }
                5 -> {
                    binding.sampleNameButton5.text = title
                    binding.seekBarVolButton5.progress = (volume * 100).toInt()
                    binding.volButton5.text = "${(volume * 100).toInt()}%"
                }
                6 -> {
                    binding.sampleNameButton6.text = title
                    binding.seekBarVolButton6.progress = (volume * 100).toInt()
                    binding.volButton6.text = "${(volume * 100).toInt()}%"
                }
                7 -> {
                    binding.sampleNameButton7.text = title
                    binding.seekBarVolButton7.progress = (volume * 100).toInt()
                    binding.volButton7.text = "${(volume * 100).toInt()}%"
                }
                8 -> {
                    binding.sampleNameButton8.text = title
                    binding.seekBarVolButton8.progress = (volume * 100).toInt()
                    binding.volButton8.text = "${(volume * 100).toInt()}%"
                }
                9 -> {
                    binding.sampleNameButton9.text = title
                    binding.seekBarVolButton9.progress = (volume * 100).toInt()
                    binding.volButton9.text = "${(volume * 100).toInt()}%"
                }
                10 -> {
                    binding.sampleNameButton10.text = title
                    binding.seekBarVolButton10.progress = (volume * 100).toInt()
                    binding.volButton10.text = "${(volume * 100).toInt()}%"
                }
            }
            i++
        }
    }
}

