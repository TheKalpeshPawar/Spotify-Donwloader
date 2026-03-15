package com.example.dettol.screens.home

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dettol.components.FloatingSearchButton
import com.example.dettol.components.HomeScreenSongDetailsCard
import com.example.dettol.components.MyTopAppBar
import com.example.dettol.navigation.Destination
import com.example.dettol.utils.milliSecondsToMinutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    val recommendedTracks
        = homeScreenViewModel.recommendations.collectAsState()

    val state = rememberPullToRefreshState()

    val isRefreshing = remember {
        mutableStateOf(false)
    }

    Surface(
        Modifier.padding(6.dp)
            .fillMaxSize()
            .consumeWindowInsets(insets = WindowInsets.statusBars),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Scaffold(
                topBar = {
                    MyTopAppBar(){
                        navController.navigate(Destination.DownloadScreen.name)

                    }
                },
                floatingActionButton = {
                    FloatingSearchButton(){
                        navController.navigate(Destination.SearchScreen.name)
                    }
                },
                modifier = Modifier.fillMaxSize()
            ){paddingValue ->

                PullToRefreshBox(
                    modifier = Modifier.padding(paddingValue),
                    state = state,
                    isRefreshing = isRefreshing.value,
                    onRefresh = {
                        isRefreshing.value = true
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1500)
                            homeScreenViewModel.getRecommendations(
                                sharedPreferences.getStringSet(
                                    "track_ids",
                                    emptySet()
                                )?.toList() ?: emptyList()
                            )
                            isRefreshing.value = false
                        }
                    },
                    indicator = {
                        PullToRefreshDefaults.Indicator(
                            state = state,
                            isRefreshing = isRefreshing.value,
                            modifier = Modifier.align(Alignment.TopCenter)
                                .size(50.dp),
                            containerColor = Color.Black,
                            color = Color.Green
                        )
                    }
                ) {
                    LaunchedEffect(recommendedTracks.value) {
                        if(
                            recommendedTracks.value?.success == false && recommendedTracks.value?.message !=null
                        ){
                            navController.navigate(Destination.ErrorScreen.name +"/${recommendedTracks.value?.message}")
                        }
                    }

                    if(recommendedTracks.value?.success !=true){
                        CircularProgressIndicator(
                            modifier.padding(top = 80.dp)
                                .fillMaxWidth()
                        )
                    } else {
                        Log.d("Recommendations",
                            recommendedTracks.value?.data?.tracks.toString()
                        )
                        Log.d("Recommendations",
                            recommendedTracks.value?.success.toString()
                        )
                        Log.d("Recommendations",
                            recommendedTracks.value?.message.toString()
                        )

                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .padding(top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(recommendedTracks.value?.data?.tracks ?: emptyList()){ track ->

                                HomeScreenSongDetailsCard(
                                    trackTitle = track.name,
                                    artist = track.artists.map { it.name }.toString()
                                        .replace("[", "")
                                        .replace("]", ""),
                                    releaseDate = track.album.release_date,
                                    duration = milliSecondsToMinutes(track.duration_ms),
                                    coverArtUrl = track.album.images[0].url
                                ){
                                    navController.navigate(Destination.SongInfoScreen.name+"/${track.id}")
                                }
                            }
                        }
                    }
                }



            }
        }

    }
}

