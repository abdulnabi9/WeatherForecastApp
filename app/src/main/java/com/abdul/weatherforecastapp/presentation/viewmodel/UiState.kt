package com.abdul.weatherforecastapp.presentation.viewmodel

import com.abdul.weatherforecastapp.domain.model.WeatherForecast

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(
        val data: List<WeatherForecast>,
        val isFromCache: Boolean = false
    ) : UiState()
    data class Error(val message: String) : UiState()
}