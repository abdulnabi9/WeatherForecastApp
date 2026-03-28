package com.abdul.weatherforecastapp.domain.repository

import com.abdul.weatherforecastapp.domain.model.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(city: String): Flow<Any>
}