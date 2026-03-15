package com.example.dettol.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview(showBackground = true)
@Composable
fun MyBottomAppBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {

    BottomAppBar(
        containerColor = Color(0xFF1DB954),
        tonalElevation = 10.dp
    ) {
        IconButton(
            onClick = onHomeClick,
            modifier = Modifier.fillMaxHeight()
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.TwoTone.Home,
                null,
                Modifier.fillMaxSize(0.4f),
                tint = Color.Black
            )
        }

        IconButton(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxHeight()
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.TwoTone.Search,
                null,
                Modifier.fillMaxSize(0.4f),
                tint = Color.Black
            )
        }

        IconButton(
            onClick = onDownloadClick,
            modifier = Modifier.fillMaxHeight()
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.TwoTone.Download,
                null,
                Modifier.fillMaxSize(0.4f),
                tint = Color.Black
            )
        }
    }

}