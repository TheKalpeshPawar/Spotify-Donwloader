package com.example.dettol.mediaPlayer

import android.content.Context
import android.media.browse.MediaBrowser.MediaItem
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun Player(
     context: Context,
     uri: Uri
) {

    val player = ExoPlayer.Builder(context).build()

    val mediaItem = androidx.media3.common.MediaItem.fromUri(
        uri
    )

    player.addMediaItem(mediaItem)

    player.prepare()

}