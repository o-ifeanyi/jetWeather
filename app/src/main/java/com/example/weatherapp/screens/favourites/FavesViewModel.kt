package com.example.weatherapp.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Favourite
import com.example.weatherapp.repository.WeatherDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavesViewModel @Inject constructor(private val weatherDaoRepository: WeatherDaoRepository) : ViewModel() {
    private val _allFavourites = MutableStateFlow<List<Favourite>>(emptyList())
    val allFavourites = _allFavourites.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDaoRepository.getFavourites().distinctUntilChanged().collect {
                _allFavourites.value = it
            }
        }
    }

    fun addFavourite(favourite: Favourite) {
        viewModelScope.launch { weatherDaoRepository.addFavourite(favourite) }
    }

    fun deleteFavourite(favourite: Favourite) {
        viewModelScope.launch { weatherDaoRepository.deleteFavourite(favourite) }
    }
}