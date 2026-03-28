package com.abdul.weatherforecastapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abdul.weatherforecastapp.presentation.components.CachedBanner
import com.abdul.weatherforecastapp.presentation.components.EmptyState
import com.abdul.weatherforecastapp.presentation.components.ErrorView
import com.abdul.weatherforecastapp.presentation.components.LoadingView
import com.abdul.weatherforecastapp.presentation.components.WeatherItem
import com.abdul.weatherforecastapp.presentation.viewmodel.UiState
import com.abdul.weatherforecastapp.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    var city by remember { mutableStateOf("") }
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top = 50.dp, end = 16.dp)
    ) {



        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Search City") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { viewModel.fetch(city) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {

            is UiState.Idle -> {
                EmptyState()
            }

            is UiState.Loading -> {
                LoadingView()
            }

            is UiState.Success -> {

                if (state.isFromCache) {
                    CachedBanner()
                }

                LazyColumn {
                    items(state.data) {


                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically()
                        ) {
                            WeatherItem(it)
                        }
                    }
                }
            }

            is UiState.Error -> {
                ErrorView(
                    message = state.message,
                    onRetry = { viewModel.fetch(city) }
                )
            }
        }
    }
}

