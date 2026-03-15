package com.example.dettol.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.dettol.components.SongCardSearch
import com.example.dettol.navigation.Destination
import com.example.network.module.tracks.Track


@Composable
fun SearchScreen(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel
) {
    val searchQuery = remember {
        mutableStateOf("")
    }

    val searchData = searchScreenViewModel.searchData.collectAsStateWithLifecycle()

    LaunchedEffect(searchData.value) {
        if(searchData.value?.success == false){
            navController.navigate(Destination.ErrorScreen.name+"${searchData.value?.message}")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                },
                shape = RoundedCornerShape(CornerSize(30.dp)),
                placeholder = {
                    Text(
                        text = "Search.."
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        searchScreenViewModel.search(searchQuery.value)
                    }
                )

            )
            Spacer(Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(
                    items = searchData.value?.data?.tracks?.items ?: emptyList<Track>()
                ){song ->
                    Log.d("SongCard",song.name)

                    SongCardSearch(
                        imageUrl = song.albumOfTrack.coverArt[0].url ?: "https://picsum.photos/200",
                        songTitle = song.name ?: "Brown Rang",
                        artists = song.artists.items.map { artist ->
                            artist.profile.name
                        }.toString()
                            .replace("[", "")
                            .replace("]", "")
                            ?: "Yo Yo Honey Singh",
                        duration = song.duration.totalMilliseconds ?:181413,
                        albumOfTrack = song.albumOfTrack.name ?:"International Villager"
                    ){
                        navController.navigate(
                            Destination.SongInfoScreen.name+"/${song.id}"
                        )
                    }
                }
            }
            
        }


    }

}