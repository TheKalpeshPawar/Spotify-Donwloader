package com.example.dettol.screens.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dettol.repositories.HomeScreenRepository
import com.example.network.module.trackDetails.trackRecommendations.TrackRecommendations
import com.example.network.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeScreenRepository,
    sharedPreferences: SharedPreferences
) : ViewModel()  {

    private val _recommendations =
        MutableStateFlow<TrackRecommendations?>(null)

    val recommendations = _recommendations.asStateFlow()


    init{

        val x = sharedPreferences.getStringSet("track_ids", emptySet())
        Log.d("Recommendation", x.toString())
        if(x.isNullOrEmpty()){
            getRecommendations(
                listOf(Constants.DEFAULT_SONG_ID)
            )
        } else {
            getRecommendations(
                x?.toList() ?: listOf(Constants.DEFAULT_SONG_ID)
            )
        }

        println(x.toString())

    }

    fun getRecommendations(
        trackIds: List<String>,
        genre: String = "",
        limit: Int = 100
    ) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            _recommendations.update {
                return@update repository.getSongRecommendation(trackIds, genre, limit)
            }
            Log.d("TrackSeeds", trackIds.toString())
        }
    }
}