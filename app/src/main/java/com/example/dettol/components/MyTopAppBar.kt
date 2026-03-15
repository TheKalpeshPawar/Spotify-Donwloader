package com.example.dettol.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dettol.R


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    onDownloadClick: () -> Unit = {}
){
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
                    stringResource(R.string.app_name),
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.Cursive,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        },
        navigationIcon = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ){
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        Icons.Outlined.Menu,
                        null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

        },
        actions = {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 10.dp)
            ){
                IconButton(
                    onClick = onDownloadClick
                ){
                    Icon(
                        Icons.Outlined.Download,
                        null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

        },
        expandedHeight = 70.dp,
    )
}