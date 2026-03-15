package com.example.dettol.mediaReader

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat


class MediaReader(
    private val context: Context
) {

    fun getAudioFiles(): List<MediaFile>{
        val buildVersion = Build.VERSION.SDK_INT

        val skipQuery = if(buildVersion <=32){
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        } else false

        if(skipQuery) return emptyList()

        val mediaFiles = mutableListOf<MediaFile>()

        val queryUri =if(Build.VERSION.SDK_INT >=29){
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.getContentUri("external")
        }

        val projection = arrayOf(
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.DISPLAY_NAME,
            MediaStore.Audio.AudioColumns.MIME_TYPE
        )

        context.contentResolver.query(
            queryUri,
            projection,
            null,
            null,
            null,
        )?.use {cursor ->

            val idColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.AudioColumns._ID
            )

            val nameColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.AudioColumns.DISPLAY_NAME
            )

            val mimeTypeColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.AudioColumns.MIME_TYPE
            )

            while (cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val mimeType = cursor.getString(mimeTypeColumn)

                if(name != null && mimeType != null){

                    val contentUri = ContentUris.withAppendedId(
                        queryUri,
                        id
                    )
                    Log.d("ContentUri", contentUri.toString())

                    mediaFiles.add(
                        MediaFile(
                            uri = contentUri,
                            name = name,
                            type = "audio"
                        )
                    )

                }

            }

        }



        return mediaFiles

    }

}