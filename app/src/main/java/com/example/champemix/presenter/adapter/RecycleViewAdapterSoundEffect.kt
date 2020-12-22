package com.example.champemix.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.champemix.databinding.ItemSoundBinding
import com.example.champemix.presenter.tools.MyPlayerSound
import com.example.champemix.utility.GetMetadata
import java.text.DecimalFormat

class RecycleViewAdapterSoundEffect(
    private val context: Context,
    private val listData: ArrayList<String>
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
        val soundEffect = listData[position]

        val idResource = context.resources.getIdentifier(soundEffect, "raw", context.packageName)
        val duration = GetMetadata().getDuration(context,idResource)

        holder.bind(soundEffect,duration)
        holder.clickListener(context,idResource)
    }

    class ViewHolder(private val binding: ItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun clickListener(context: Context, idResource: Int){
            binding.PlaySample.setOnClickListener {
                MyPlayerSound().playSound(idResource, context,1f)
            }

            binding.loadEffectSample.setOnClickListener {
                Toast.makeText(context, "cargar button", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(resource: String, duration: Int) {

            val format = DecimalFormat()
            format.maximumFractionDigits = 2

            val duration = format.format(duration.toFloat() / 1000.0)

            binding.titleSample.text = resource.replace('_',' ')
            binding.DurationSample.text = "Duration: ${duration}s"
        }
    }
}
