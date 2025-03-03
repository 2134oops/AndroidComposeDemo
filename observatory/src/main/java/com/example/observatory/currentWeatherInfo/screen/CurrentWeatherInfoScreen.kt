package com.example.observatory.currentWeatherInfo.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrentWeatherInfoScreen() {
    val viewModel = koinViewModel<CurrentWeatherInfoViewModel>()
    viewModel
    Scaffold { padding ->
        Text(
            modifier = Modifier.padding(padding),
            text = ""
        )
    }
}