package com.abdul.weatherforecastapp.presentation.components


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdul.weatherforecastapp.presentation.screen.WeatherScreen
import com.abdul.weatherforecastapp.presentation.viewmodel.WeatherViewModel
import com.abdul.weatherforecastapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherAppRoot() {

    val viewModel: WeatherViewModel = hiltViewModel()

    WeatherAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherScreen(viewModel)
        }
    }
}