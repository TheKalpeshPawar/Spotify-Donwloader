package com.example.dettol.mediaReader

import android.net.Uri

data class MediaFile(
    val uri: Uri,
    val name: String,
    val type: String
)
