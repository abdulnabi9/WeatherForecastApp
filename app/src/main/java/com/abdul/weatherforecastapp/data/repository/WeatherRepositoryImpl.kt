package com.abdul.weatherforecastapp.data.repository

import android.util.Log
import com.abdul.weatherforecastapp.data.local.WeatherDao
import com.abdul.weatherforecastapp.data.local.WeatherEntity
import com.abdul.weatherforecastapp.data.remote.ForecastItem
import com.abdul.weatherforecastapp.data.remote.WeatherApi
import com.abdul.weatherforecastapp.domain.model.WeatherForecast
import com.abdul.weatherforecastapp.domain.repository.WeatherRepository
import com.abdul.weatherforecastapp.presentation.viewmodel.UiState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao,
    @javax.inject.Named("api_key") private val apiKey: String
) : WeatherRepository {

    override fun getWeather(city: String) = flow<UiState> {

        if (city.isBlank()) {
            emit(UiState.Error("Please enter a city name"))
            return@flow
        }

        try {
            val response = api.getForecast(city.trim(), apiKey)

            val filtered = filter3Days(response.list)

            if (filtered.isEmpty()) {
                emit(UiState.Error("City not found "))
                return@flow
            }


            dao.delete(city)
            dao.insertAll(filtered.map { it.toEntity(city) })

            emit(UiState.Success(filtered, false))

        } catch (e: retrofit2.HttpException) {

            if (e.code() == 404) {
                emit(UiState.Error("City not found "))
            } else {
                val code = e.code()
                val msg = e.response()?.errorBody()?.string()


                Log.d("Error","Error $code: $msg")
               emit(UiState.Error("Server error"))
            }

        } catch (e: java.io.IOException) {


            val cached = dao.getWeather(city)

            if (cached.isNotEmpty()) {
                emit(UiState.Success(cached.map { it.toDomain() }, true))
            } else {
                emit(UiState.Error("No internet connection "))
            }
        }
    }
}
fun WeatherForecast.toEntity(city: String) = WeatherEntity(
    date, city, temp, condition, icon
)

fun WeatherEntity.toDomain() = WeatherForecast(
    date, temp, condition, icon
)

fun filter3Days(list: List<ForecastItem>): List<WeatherForecast> {

    val result = mutableListOf<WeatherForecast>()
    val addedDates = mutableSetOf<String>()

    for (item in list) {
        val (date, time) = item.dt_txt.split(" ")

        if (time == "12:00:00" && date !in addedDates) {
            result.add(
                WeatherForecast(
                    date = date,
                    temp = item.main.temp,
                    condition = item.weather.first().main,
                    icon = item.weather.first().icon
                )
            )
            addedDates.add(date)
        }

        if (result.size == 3) break
    }

    return result
}