package com.example.weatherapp.data

import androidx.room.*
import com.example.weatherapp.models.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query(value = "SELECT * from fav_tbl")
    fun getFavourites() : Flow<List<Favourite>>

    @Insert(entity = Favourite::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(favourite: Favourite)

    @Delete(entity = Favourite::class)
    suspend fun deleteFavourite(favourite: Favourite)
}