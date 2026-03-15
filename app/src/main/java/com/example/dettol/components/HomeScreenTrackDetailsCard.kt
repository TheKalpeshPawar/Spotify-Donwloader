package com.example.dettol.components

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dettol.utils.formatDate


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreenSongDetailsCard(
    modifier: Modifier = Modifier,
    trackTitle: String = "APT.Arabic Version",
    artist: String = "",
    releaseDate: String = "",
    duration: String = "",
    coverArtUrl: String = "",
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .height(390.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                onClick(trackTitle)
            },
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            Color(randomSpotifyPaletteThreeColor())
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(
                        text = trackTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                )
                            ) {
                                this.append("Track")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    baselineShift = BaselineShift(-0.2F)
                                )
                            ) {
                                append("•")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White
                                )
                            ) {
                                append(artist)
                            }
                        },
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.TwoTone.AddCircle,
                    contentDescription = "Add Icon",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(170.dp)
                ) {
                    GlideImage(
                        model = coverArtUrl,
                        contentDescription = "Track Cover Art",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                        )
                    ) {
                        this.append(formatDate(releaseDate))

                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            baselineShift = BaselineShift(-0.2F)
                        )
                    ) {
                        append("•")
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("$duration min")
                    }
                },
                fontSize = 14.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenSongDetailsCardPreview() {
    HomeScreenSongDetailsCard(
        trackTitle = "APT.Arabic Version",
        artist = "LGM's Podcast",
        coverArtUrl = "https://i.scdn.co/image/ab67616d00001e0268dfdf1e56aa2a952f5c52e8",
        releaseDate = "Jan 01",
        duration = "15 min"
    )
}