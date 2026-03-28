package com.abdul.weatherforecastapp.data.remote

data class ForecastItem(
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)