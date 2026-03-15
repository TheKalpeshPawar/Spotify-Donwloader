package com.example.network.module

import com.example.network.module.albums.Albums
import com.example.network.module.albums.Artists
import com.example.network.module.tracks.Tracks
import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    val albums: Albums?,
    val artists: Artists?,
    val tracks: Tracks?
)