package com.example.observatory.currentWeatherInfo.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrentWeatherInfoScreen() {
    val viewModel = koinViewModel<CurrentWeatherInfoViewModel>()
    val state = viewModel.weatherInfoState.collectAsState()
    Log.d("weather_state", state.value.toString())
    Scaffold { padding ->
        Text(
            modifier = Modifier.padding(padding),
            text = ""
        )
    }
}