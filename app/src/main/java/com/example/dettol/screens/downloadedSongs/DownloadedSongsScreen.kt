package com.example.dettol.screens.downloadedSongs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dettol.components.AudioFilesListCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadedSongsScreen(
    modifier: Modifier = Modifier,
    downloadedSongsViewModel: DownloadedSongsViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = modifier
                    .height(64.dp)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(50)),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(0xFF1DB954)),
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxHeight()
                    ){

                        Text(
                            "Downloads",
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily.Cursive,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                expandedHeight = 70.dp,
            )
        },
        modifier =
        Modifier.consumeWindowInsets(insets = WindowInsets.statusBars),


    ){
        LazyColumn(
            contentPadding = it
        ) {

             items(downloadedSongsViewModel.files){
                 mediaFile ->

                 AudioFilesListCard(
                     file = mediaFile,
                     modifier = modifier
                 )
             }

        }
    }

}