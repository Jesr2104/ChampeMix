package com.example.champemix.presenter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.champemix.databinding.ItemSoundBinding
import com.example.champemix.presenter.tools.MyPlayerSound
import com.example.champemix.utility.GetMetadata
import com.example.champemix.utility.LoadEffectSound
import java.text.DecimalFormat

class RecycleViewAdapterSoundEffect(
    private val context: Context,
    private val listData: ArrayList<String>,
    private val view: LoadEffectSound
):
    RecyclerView.Adapter<RecycleViewAdapterSoundEffect.ViewHolder>() {

    override fun getItemCount() = listData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemSoundBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val soundEffectName = listData[position]

        val idResource = context.resources.getIdentifier(soundEffectName, "raw", context.packageName)
        val duration = GetMetadata().getDuration(context,idResource)

        holder.bind(soundEffectName,duration)
        holder.clickListener(context, idResource, view, soundEffectName)
    }

    class ViewHolder(private val binding: ItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun clickListener(
            context: Context,
            idResource: Int,
            view: LoadEffectSound,
            soundEffect: String
        ){
            binding.PlaySample.setOnClickListener {
                MyPlayerSound().playSound(idResource, context,1f)
            }

            binding.loadEffectSample.setOnClickListener {
                view.loadData(soundEffect)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(resource: String, duration: Int) {

            val format = DecimalFormat()
            format.maximumFractionDigits = 2

            val duration = format.format(duration.toFloat() / 1000.0)

            binding.titleSample.text = resource.replace('_',' ')
            binding.DurationSample.text = "Duration: ${duration}s"
        }
    }
}
