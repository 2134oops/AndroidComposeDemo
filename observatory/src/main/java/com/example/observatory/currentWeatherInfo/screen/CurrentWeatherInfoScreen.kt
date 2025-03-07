package com.example.observatory.currentWeatherInfo.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.observatory.currentWeatherInfo.component.DropDownPicker
import com.example.observatory.currentWeatherInfo.component.TemperatureInfo
import com.example.observatory.currentWeatherInfo.component.TemperatureInfoView
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import com.example.observatory.network.model.HongKongLocationTemperature
import com.rc.base.common.RcAppState
import com.rc.base.model.stateModel.ResultState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherInfoScreen(appState: RcAppState, viewModel: CurrentWeatherInfoViewModel) {
    val state by viewModel.weatherInfoState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingStateFlow.collectAsStateWithLifecycle()
    val locationPickerList by viewModel.locationListStateFlow.collectAsStateWithLifecycle()
    val dropdownListSelectedValue by viewModel.selectedLocationState.collectAsStateWithLifecycle()
    val temperatureInfo by viewModel.temperatureInfoStateFlow.collectAsStateWithLifecycle()
    CurrentWeatherInfoScreenContent(
        weatherInfoState = state,
        locationList = locationPickerList,
        isLoading = isLoading,
        temperatureInfo = temperatureInfo,
        dropdownListSelectedValue = dropdownListSelectedValue,
        dropdownListOnSelect = (viewModel::updateSelectedLocation)
    )
}


@Composable
internal fun CurrentWeatherInfoScreenContent(
    modifier: Modifier = Modifier,
    weatherInfoState: ResultState<HongKongLocationTemperature>,
    isLoading: Boolean = false,
    locationList: List<String>,
    temperatureInfo: TemperatureInfo,
    dropdownListSelectedValue: String,
    dropdownListOnSelect: (location: String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = {
            ObservatoryAppBar()
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TemperatureInfoView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio((4/3).toFloat()),
                    data = temperatureInfo
                )
                DropDownPicker(
                    modifier= Modifier.fillMaxWidth(),
                    valueList = locationList,
                    selectedValue = dropdownListSelectedValue,
                    onSelect = dropdownListOnSelect
                )
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ObservatoryAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier
            .shadow(elevation = 12.dp)
            .clip(RoundedCornerShape(bottomStart = 12f, bottomEnd = 12f)),
        title = {
            Text("Current Weather Info")
        },
        colors = TopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = Color.Black,
        )
    )
}

@Preview
@Composable
private fun PreviewObservatoryAppBar() {
    ObservatoryAppBar()
}

@Preview
@Composable
private fun PreviewCurrentWeatherInfoScreenView() {
    CurrentWeatherInfoScreenContent(
        weatherInfoState = ResultState.OnSuccess(data = HongKongLocationTemperature.dummy()),
        locationList = listOf("Hong Kong"),
        dropdownListSelectedValue = "",
        temperatureInfo = TemperatureInfo.placeholder(),
        dropdownListOnSelect = {}
    )
}