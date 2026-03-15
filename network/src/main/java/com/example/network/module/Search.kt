package com.example.network.module

import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val data: SearchData?,
    val success: Boolean,
    val message: String?
)