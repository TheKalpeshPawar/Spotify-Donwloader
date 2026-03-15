package com.example.dettol.repositories

import com.example.network.KtorClient
import javax.inject.Inject


class TrackDetailRepository @Inject constructor(
    private val ktorClient: KtorClient
) {
    suspend fun trackDetails(trackId: String)= ktorClient.trackDetails(trackId)
}