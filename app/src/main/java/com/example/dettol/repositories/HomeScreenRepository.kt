package com.example.dettol.repositories

import com.example.network.KtorClient
import javax.inject.Inject


class HomeScreenRepository @Inject constructor(
    private val ktorClient: KtorClient
) {

    suspend fun getSongRecommendation(
        trackIds: List<String>,
        genre: String = "",
        limit: Int = 100
    )= ktorClient.getTrackRecommendations(trackIds,genre, limit)

}