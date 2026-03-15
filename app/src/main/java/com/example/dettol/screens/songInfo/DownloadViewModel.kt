package com.example.dettol.screens.songInfo

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dettol.repositories.DownloadRepository
import com.example.network.SongDownloader
import com.example.network.module.download.Download
import com.example.network.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val songDownloader: SongDownloader,
    private val downloadRepository: DownloadRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _downloadInfo = MutableStateFlow(
        Download(
            data = null,
            success = false,
            message = null
        )
    )

    val downloadInfo = _downloadInfo.asStateFlow()
/*
    val TRACK_SEEDS =
        stringPreferencesKey("track_seeds")

    private suspend fun saveToDataStore(trackId: String){
        dataStore.edit { mutablePreferences ->

            val currentSeedsValue = mutablePreferences[TRACK_SEEDS] ?: ""

            if(currentSeedsValue.split(",").size == 10){
                val x = currentSeedsValue.split(",")
                    .toMutableList()
                x.removeAt(0)
            }

            mutablePreferences[TRACK_SEEDS] = "$currentSeedsValue, $trackId"
        }
    }
*/

    suspend fun downloadSongData(trackId: String){

        viewModelScope.async(Dispatchers.IO) {
            _downloadInfo.value = Download(
                data = null,
                success = false,
                message = null
            )
            _downloadInfo.update{
                 return@update downloadRepository.downloadSong(trackId)
            }
            val currentSeeds = sharedPreferences?.getStringSet("track_ids", emptySet())

            val mutableTrackSeedsSet= currentSeeds?.toMutableSet()
            if(
                mutableTrackSeedsSet?.size == 10
            ){
                val firstTrackSeed = currentSeeds.first()
                mutableTrackSeedsSet.remove(firstTrackSeed)
            }
            sharedPreferences.edit(commit = true) {
                putStringSet("track_ids", (mutableTrackSeedsSet?: emptySet()) + setOf(trackId))
            }

        }
    }

    fun downloadSong(){
        songDownloader.downloadFile(
            _downloadInfo.value.data?.downloadLink?: Constants.DEFAULT_DOWNLOAD_LINK,
            _downloadInfo.value.data?.title?: Constants.DEFAULT_DOWNLOAD_LINK.substring(0..4)
        )
    }

}