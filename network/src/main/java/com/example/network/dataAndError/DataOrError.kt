package com.example.network.dataAndError

import kotlinx.serialization.Serializable

@Serializable
data class DataOrError<D>(
    val data: D?= null,
    val success: Boolean = true,
    val message: String? = null
)