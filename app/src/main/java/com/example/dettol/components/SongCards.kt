package com.example.dettol.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dettol.utils.milliSecondsToMinutes


@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun SongCardSearch(
    imageUrl:String = "https://picsum.photos/200",
    songTitle: String = "Brown Rang",
    artists: String = "Yo Yo Honey Singh",
    duration: Int = 181413,
    albumOfTrack: String = "International Villager",
    onClick: () -> Unit = {}
){

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .background(Color.Black.copy(alpha = .3f))
            .clip(RoundedCornerShape(10.dp))
            .border(3.dp, Color.Green)
            .clickable { onClick() },
        contentAlignment = Alignment.TopStart
    ){
        Row(modifier = Modifier.padding(5.dp)){

            GlideImage(
                model = imageUrl,
                contentDescription = "Song poster",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(10.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Cyan.copy(alpha = .35f))
                    .padding(8.dp),
            ){
                Text(
                    text = songTitle,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = artists,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top= 2.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Text(
                    text = "Album: $albumOfTrack" ,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top= 2.dp, bottom = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Text(
                    text = milliSecondsToMinutes(milliseconds = duration),
                    modifier = Modifier
                        .padding(top= 2.dp)
                )

            }

        }
    }

}