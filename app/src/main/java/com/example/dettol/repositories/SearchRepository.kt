package com.example.dettol.repositories

import com.example.network.KtorClient
import javax.inject.Inject


class SearchRepository @Inject constructor(private val ktorClient: KtorClient){

    suspend fun search(query: String)=  ktorClient.search(query)
}