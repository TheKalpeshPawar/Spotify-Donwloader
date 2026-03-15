package com.example.dettol.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.dettol.R
import com.example.dettol.mediaReader.MediaFile


@Composable
fun AudioFilesListCard(
    file: MediaFile,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .height(120.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(R.drawable.music_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = file.name,
            modifier = Modifier.padding(16.dp)
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}