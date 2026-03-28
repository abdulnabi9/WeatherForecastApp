package com.abdul.weatherforecastapp.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdul.weatherforecastapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repo: WeatherRepository
) : ViewModel() {

    var state: UiState by mutableStateOf(UiState.Idle)
        private set

    fun fetch(city: String) {

        if (city.isBlank()) {
            state = UiState.Error("Enter a valid city")
            return
        }
        viewModelScope.launch {
            state = UiState.Loading



            repo.getWeather(city).collect {
                state = it as UiState
            }

        }
    }
}
