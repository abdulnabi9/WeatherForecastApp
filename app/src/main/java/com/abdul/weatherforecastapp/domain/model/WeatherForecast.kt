package com.abdul.weatherforecastapp.domain.model

data class WeatherForecast(
    val date: String,
    val temp: Double,
    val condition: String,
    val icon: String
)