package com.example.champemix.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.champemix.R
import com.example.champemix.databinding.FragmentButtonsBinding
import com.example.champemix.presenter.ButtonsPresenter
import com.example.champemix.utility.SoundButton
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.schedule

class FragmentButtons : Fragment(), ButtonsPresenter.View {

    private val buttonsPresenter = ButtonsPresenter()
    private lateinit var binding: FragmentButtonsBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentButtonsBinding.inflate(layoutInflater)

        // initialization of the presenter
        buttonsPresenter.onCreate(this, context!!.applicationContext)

        binding.nameSampleButton1.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(requireContext().applicationContext, 0)

                    binding.checkButton1.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton1.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton2.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 1)

                    binding.checkButton2.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton2.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton3.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 2)

                    binding.checkButton3.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton3.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton4.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 3)

                    binding.checkButton4.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton4.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton5.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 4)

                    binding.checkButton5.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton5.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton6.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 5)

                    binding.checkButton6.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton6.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton7.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 6)

                    binding.checkButton7.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton7.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton8.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 7)

                    binding.checkButton8.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton8.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton9.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 8)

                    binding.checkButton9.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton9.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton10.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 9)

                    binding.checkButton10.setTextColor(resources.getColor(R.color.Accept))
                    Timer().schedule(50){
                        binding.checkButton10.setTextColor(resources.getColor(R.color.red_error))
                    }

                    false
                }
                else -> false
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        buttonsPresenter.onDestroy()
        super.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    override fun loadData(data: ArrayList<SoundButton>) {

        var i = 1
        for (item in data) {

            val title = item.titleSound
            val format = DecimalFormat()
            format.maximumFractionDigits = 2

            val duration = format.format(item.duration.toFloat() / 1000.0)

            when(i){
                1 ->{
                    binding.nameSampleButton1.text = title
                    binding.timeButton1.text = "T: ${duration}s"
                    binding.volumeButton1.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                2 ->{
                    binding.nameSampleButton2.text = title
                    binding.timeButton2.text = "T: ${duration}s"
                    binding.volumeButton2.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                3 ->{
                    binding.nameSampleButton3.text = title
                    binding.timeButton3.text = "T: ${duration}s"
                    binding.volumeButton3.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                4 ->{
                    binding.nameSampleButton4.text = title
                    binding.timeButton4.text = "T: ${duration}s"
                    binding.volumeButton4.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                5 ->{
                    binding.nameSampleButton5.text = title
                    binding.timeButton5.text = "T: ${duration}s"
                    binding.volumeButton5.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                6 ->{
                    binding.nameSampleButton6.text = title
                    binding.timeButton6.text = "T: ${duration}s"
                    binding.volumeButton6.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                7 ->{
                    binding.nameSampleButton7.text = title
                    binding.timeButton7.text = "T: ${duration}s"
                    binding.volumeButton7.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                8 ->{
                    binding.nameSampleButton8.text = title
                    binding.timeButton8.text = "T: ${duration}s"
                    binding.volumeButton8.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                9 ->{
                    binding.nameSampleButton9.text = title
                    binding.timeButton9.text = "T: ${duration}s"
                    binding.volumeButton9.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
                10 ->{
                    binding.nameSampleButton10.text = title
                    binding.timeButton10.text = "T: ${duration}s"
                    binding.volumeButton10.text = "Vol. ${(item.volume * 100).toInt()}%"
                }
            }
            i++
        }
    }
}