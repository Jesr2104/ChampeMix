package com.just_jump.champemix.presenter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.just_jump.champemix.R
import com.just_jump.champemix.databinding.ItemSongBinding
import com.just_jump.champemix.utility.GetMetadata
import com.just_jump.champemix.utility.LoadSong
import com.just_jump.champemix.utility.PlayerSample
import java.text.DecimalFormat

class RecycleViewAdapterSong(
    private val context: Context,
    private val listData: ArrayList<String>,
    private val view: LoadSong,
    private val playerSong: PlayerSample
) :
    RecyclerView.Adapter<RecycleViewAdapterSong.ViewHolder>() {

    var isPlaying = false
    var currentPlayingPosition = -1

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
        val duration = GetMetadata().getDuration(context, idResource)

        holder.bind(songName, duration)

        if (position != currentPlayingPosition){
            holder.getBinding().PlaySample.setImageResource(R.drawable.ic_play)
        }

        holder.getBinding().PlaySample.setOnClickListener {
            playerSong.playSong(idResource)

            if (currentPlayingPosition == -1){
                holder.getBinding().PlaySample.setImageResource(R.drawable.ic_pause)
                isPlaying = true
            } else if (currentPlayingPosition == position) {
                if (isPlaying){
                    holder.getBinding().PlaySample.setImageResource(R.drawable.ic_play)
                    isPlaying = false
                } else {
                    holder.getBinding().PlaySample.setImageResource(R.drawable.ic_pause)
                    isPlaying = true
                }
            } else {
                holder.getBinding().PlaySample.setImageResource(R.drawable.ic_pause)
                isPlaying = true
            }


            currentPlayingPosition = position
            notifyDataSetChanged()
        }
        holder.clickListener(view, songName)

    }

    class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getBinding() = binding

        fun clickListener(view: LoadSong,songName: String) {
            binding.loadEffectSample.setOnClickListener {
                view.recycleViewControlEventSong(songName)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(resource: String, duration: Int) {

            val format = DecimalFormat()
            format.maximumFractionDigits = 2

            val duration = format.format(duration.toFloat() / 1000.0)

            binding.titleSample.text = resource.replace('_', ' ')
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
