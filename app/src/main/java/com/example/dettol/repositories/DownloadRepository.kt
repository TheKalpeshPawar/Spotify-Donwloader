package com.example.dettol.repositories

import com.example.network.KtorClient
import javax.inject.Inject

class DownloadRepository @Inject constructor(
    private val ktorClient: KtorClient,
) {

    suspend fun downloadSong(trackId: String) =
        ktorClient.downloadSong(trackId)

}