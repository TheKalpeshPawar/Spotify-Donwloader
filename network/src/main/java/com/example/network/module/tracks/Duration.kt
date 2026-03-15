package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class Duration(
    val totalMilliseconds: Int
)