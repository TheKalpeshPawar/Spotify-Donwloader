package com.example.dettol.screens.downloadedSongs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dettol.mediaReader.MediaFile
import com.example.dettol.mediaReader.MediaReader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DownloadedSongsViewModel @Inject constructor(
    private val mediaReader: MediaReader
): ViewModel() {

    var files by mutableStateOf(listOf<MediaFile>())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            files = mediaReader.getAudioFiles()
        }
    }

}