package com.example.network.module.download

import kotlinx.serialization.Serializable


@Serializable
data class Download(
    val data: Data?,
    val success: Boolean,
    val message: String?
)