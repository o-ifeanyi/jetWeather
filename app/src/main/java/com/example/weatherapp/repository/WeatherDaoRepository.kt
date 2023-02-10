package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.models.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDaoRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavourites() : Flow<List<Favourite>> = weatherDao.getFavourites()

    suspend fun addFavourite(favourite: Favourite) = weatherDao.addToFavourite(favourite)

    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite)
}
