package com.abdul.weatherforecastapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<WeatherEntity>)

    @Query("SELECT * FROM weather WHERE city = :city")
    suspend fun getWeather(city: String): List<WeatherEntity>



    @Query("DELETE FROM weather WHERE city = :city")
    suspend fun delete(city: String)
}