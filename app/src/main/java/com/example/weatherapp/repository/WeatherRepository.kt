package com.example.weatherapp.repository

import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.utils.DataOrException
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(city: String) : DataOrException<WeatherModel, Exception> {
        val response = DataOrException<WeatherModel, Exception>()
        try {
            response.data = api.getWeather(q = city)
        } catch (exc: Exception) {
            response.exc = exc
        }
        return  response
    }
}