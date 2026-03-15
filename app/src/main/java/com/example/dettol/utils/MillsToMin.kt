package com.example.dettol.utils

import kotlin.math.*

fun milliSecondsToMinutes(milliseconds: Int): String {
    val aMinInMilliseconds = 60000f

    val minutes : Float = milliseconds.div(aMinInMilliseconds)
    val min = minutes.toInt()
    val seconds  = (minutes.minus(min) * 60).roundToInt()
    val sec = if((minutes.minus(min) * 60)/10<1) "0$seconds" else seconds
    return "$min:$sec"
}