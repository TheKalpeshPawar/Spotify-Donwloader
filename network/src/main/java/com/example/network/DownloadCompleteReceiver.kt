package com.example.network

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DownloadCompleteReceiver: BroadcastReceiver() {

    private lateinit var downloadManager: DownloadManager

    override fun onReceive(context: Context?, intent: Intent?) {

        downloadManager = context?.getSystemService(DownloadManager::class.java)!!

        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE"){
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            Log.d("DownloadedSuccessfully","Download with ID $id finished!")
//            if(id!= -1L){
//
//            } else{
//                Log.d("DownloadFailed","Download with ID $id failed!")
//            }
        }
    }

}