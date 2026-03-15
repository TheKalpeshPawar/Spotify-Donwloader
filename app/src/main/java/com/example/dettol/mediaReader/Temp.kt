package com.example.dettol.mediaReader

import android.content.Context
import android.os.Environment

class Temp(
    private val context: Context
) {

    val directory = context.getExternalFilesDirs(
        Environment.DIRECTORY_MUSIC
    )



}