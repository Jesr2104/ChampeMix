package com.example.champemix.utility

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri

class GetMetadata{
    fun getDuration(context: Context, resource: Int): Int {
        var retriever: MediaMetadataRetriever? = null
        val path = Uri.parse("android.resource://${context.packageName}/${resource}")

        retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, path)
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)

        retriever.release()

        if (time == null)
        {
            return 0
        }
        return time.toInt()
    }

    fun getTitle(context: Context, resource: Int): String{
        val retriever: MediaMetadataRetriever?
        val path = Uri.parse("android.resource://${context.packageName}/${resource}")

        retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, path)
        var title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)

        retriever.release()

        if (title == null){
            title = "unknown"
        }
        return title
    }
}

