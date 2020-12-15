package com.example.champemix.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.champemix.databinding.FragmentButtonsBinding
import com.example.champemix.presenter.ButtonsPresenter

class FragmentButtons : Fragment(), ButtonsPresenter.View {

    private val buttonsPresenter = ButtonsPresenter()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentButtonsBinding.inflate(layoutInflater)

        // initialization of the presenter
        buttonsPresenter.onCreate(this)

        binding.nameSampleButton1.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(requireContext().applicationContext, 1)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton2.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 2)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton3.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 3)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton4.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 4)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton5.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 5)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton6.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 6)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton7.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 7)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton8.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 8)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton9.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 9)
                    false
                }
                else -> false
            }
        }

        binding.nameSampleButton10.setOnTouchListener { _: View, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    buttonsPresenter.playerSound(activity!!.applicationContext, 0)
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
}