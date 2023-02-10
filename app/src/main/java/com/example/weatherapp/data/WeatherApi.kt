package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query(value = "q") q: String,
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appid: String = Constants.API_KEY,
    ): WeatherModel
}