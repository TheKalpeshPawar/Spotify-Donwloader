package com.example.dettol.screens.songInfo

import androidx.lifecycle.ViewModel
import com.example.dettol.repositories.TrackDetailRepository
import com.example.network.module.trackDetails.TrackDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SongInfoViewModel @Inject constructor(private val repository: TrackDetailRepository): ViewModel() {

//    private val _songDetails = MutableStateFlow<TrackDetails?>(null)

//    val songDetails = _songDetails.asStateFlow()

    suspend fun trackDetails(trackId: String): TrackDetails{

        return repository.trackDetails(trackId)

    }



}