package com.abdul.weatherforecastapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CachedBanner() {
    Text(
        text = "Showing offline data",
        color = Color.Gray,
        modifier = Modifier.padding(8.dp)
    )
}