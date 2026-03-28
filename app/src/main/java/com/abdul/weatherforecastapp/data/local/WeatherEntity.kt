package com.abdul.weatherforecastapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val date: String,
    val city: String,
    val temp: Double,
    val condition: String,
    val icon: String,
    val timestamp: Long = System.currentTimeMillis()
)