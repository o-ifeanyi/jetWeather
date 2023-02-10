package com.example.weatherapp.models

data class WeatherModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherObject>,
    val message: Int
)