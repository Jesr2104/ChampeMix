package com.example.champemix.presenter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.champemix.databinding.ItemSongBinding
import com.example.champemix.presenter.tools.MyPlayerSound
import com.example.champemix.utility.GetMetadata
import com.example.champemix.utility.LoadSong
import java.text.DecimalFormat

class RecycleViewAdapterSong(
    private val context: Context,
    private val listData: ArrayList<String>,
    private val view: LoadSong
):
    RecyclerView.Adapter<RecycleViewAdapterSong.ViewHolder>() {

    override fun getItemCount() = listData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songName = listData[position]

        val idResource = context.resources.getIdentifier(songName, "raw", context.packageName)
        val duration = GetMetadata().getDuration(context,idResource)

        holder.bind(songName,duration)
        holder.clickListener(context, idResource, view, songName)
    }

    class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun clickListener(
            context: Context,
            idResource: Int,
            view: LoadSong,
            songName: String
        ){
            binding.PlaySample.setOnClickListener {
                MyPlayerSound().playSound(idResource, context,1f)
            }

            binding.loadEffectSample.setOnClickListener {
                view.recycleViewControlEventSong(songName)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(resource: String, duration: Int) {

            val format = DecimalFormat()
            format.maximumFractionDigits = 2

            val duration = format.format(duration.toFloat() / 1000.0)

            binding.titleSample.text = resource.replace('_',' ')
            binding.titleSample.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.titleSample.setHorizontallyScrolling(true)
            binding.titleSample.marqueeRepeatLimit = -1
            binding.titleSample.isSelected = true
            binding.titleSample.isFocusable = true
            binding.titleSample.isFocusableInTouchMode = true
            binding.DurationSample.text = "Duration: ${duration}s"
        }
    }
}
