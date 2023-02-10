package com.example.weatherapp.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    val weatherState = mutableStateOf(false)
    val weatherData = mutableStateOf(DataOrException<WeatherModel, Exception>())


    fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            weatherState.value = true
            weatherData.value = weatherRepository.getWeather(city)
            if (weatherData.value.exc != null) {
                Log.d("ERROR", weatherData.value.exc.toString())
            }
            weatherState.value = false
        }
    }
}