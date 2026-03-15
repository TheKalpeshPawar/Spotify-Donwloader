package com.example.network

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri

class SongDownloader(context: Context): Downloader{

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, songName: String): Long {
        val downloadRequest = DownloadManager.Request(url.toUri())
            .setMimeType("audio/mpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "Music/$songName.mp3"
            )

        val downloadId = downloadManager.enqueue(downloadRequest)
        Log.d("DownloadId", "$downloadId")

        return  downloadId
    }

}