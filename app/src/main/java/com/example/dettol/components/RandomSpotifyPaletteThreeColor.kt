package com.example.dettol.components

import com.example.dettol.R


fun randomSpotifyPaletteThreeColor(): Int{
    val colors = listOf(
        R.color.indian_yellow,
        R.color.brown_chocolate,
        R.color.laurel_green,
        R.color.myrtle_green,
        R.color.brandy,
        R.color.cadet_grey,
        R.color.stormcloud,
        R.color.ruddy_brown,
    )
    return colors.random()
}
