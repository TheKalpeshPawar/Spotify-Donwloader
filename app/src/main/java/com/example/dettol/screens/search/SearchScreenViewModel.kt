package com.example.dettol.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dettol.repositories.SearchRepository
import com.example.network.module.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val repository: SearchRepository) :
    ViewModel() {

    private val _searchData =
        MutableStateFlow<Search?>(null)

    val searchData = _searchData.asStateFlow()

    fun search(query: String){

        viewModelScope.launch(Dispatchers.IO) {

            _searchData.value = repository.search(query)

        }
    }

}