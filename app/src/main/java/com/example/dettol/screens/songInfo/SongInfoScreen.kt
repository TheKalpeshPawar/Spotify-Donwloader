package com.example.dettol.screens.songInfo

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dettol.R
import com.example.dettol.navigation.Destination
import com.example.dettol.utils.milliSecondsToMinutes
import com.example.network.module.trackDetails.TrackDetails
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SongInfoScreen (
    songID: String = "6AsOmRcEY4jgLZCuQwKe7w",
    songInfoViewModel: SongInfoViewModel,
    downloadViewModel: DownloadViewModel,
    navController: NavController,
    sharedPreferences: SharedPreferences
){

    val songDownloadData = downloadViewModel.downloadInfo.collectAsState()

    LaunchedEffect(key1 = songDownloadData.value) {
        if(
            songDownloadData.value?.success!=true &&
            songDownloadData.value?.message!=null
        ){
            navController.navigate(Destination.ErrorScreen.name+"/${songDownloadData.value?.message}")
        } else if(
            songDownloadData.value?.success==true
        ) {
            Log.d("DownloadData",songDownloadData.value?.data.toString())
            async(Dispatchers.IO) {
                downloadViewModel.downloadSong()
            }.await()
        }
    }

    val songInfo = produceState<TrackDetails?>(
        initialValue = null
    ){
        value = songInfoViewModel.trackDetails(songID)
    }.value

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        if (songInfo == null){
            CircularProgressIndicator()
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(250.dp)
            ){

                GlideImage(
                    model = songInfo.data?.tracks?.get(0)?.album?.images?.get(0)?.url ?: R.drawable.dummysongposter,
                    contentDescription = "Track cover art",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                songInfo?.data?.tracks?.get(0)?.name ?: "Song Name",
                fontSize = 32.sp
            )
            HorizontalDivider(Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(20.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(Modifier.height(10.dp))
                Text(
                    "Artists: ${
                        songInfo.data?.tracks?.get(0)?.artists?.map { it.name }.toString()
                            .replace("[", "")
                            .replace("]", "")
                    }",
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    "Album: ${songInfo?.data?.tracks?.get(0)?.album?.name}",
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    "Album Release Date: ${songInfo?.data?.tracks?.get(0)?.album?.release_date}"
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    "Duration: ${milliSecondsToMinutes(
                        milliseconds = songInfo?.data?.tracks?.get(0)?.duration_ms ?: 0
                    )}",
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {
                    runBlocking {
                        downloadViewModel
                            .downloadSongData(
                            songInfo?.data?.tracks?.get(0)?.id ?: Constants.DEFAULT_SONG_ID
                        )
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
                    .height(40.dp)
            ){
                Text("Download")
            }
        }
    }

}