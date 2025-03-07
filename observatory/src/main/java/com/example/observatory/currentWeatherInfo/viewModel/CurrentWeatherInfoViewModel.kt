package com.example.observatory.currentWeatherInfo.viewModel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.util.TimeUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.observatory.currentWeatherInfo.component.TemperatureInfo
import com.example.observatory.currentWeatherInfo.usecase.CurrentWeatherInfoUseCase
import com.example.observatory.network.model.HongKongLocationTemperature
import com.example.observatory.network.model.HumidityData
import com.example.observatory.network.model.RainFallData
import com.example.observatory.network.model.Temperature
import com.example.observatory.repository.ObservatoryRepository
import com.rc.base.model.stateModel.ResultState
import com.rc.base.model.stateModel.onError
import com.rc.base.model.stateModel.onInit
import com.rc.base.model.stateModel.onLoading
import com.rc.base.model.stateModel.onSuccess
import com.rc.base.util.todayDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.time.LocalDate

class CurrentWeatherInfoViewModel(
    application: Application,
    private val currentWeatherInfoUseCase: CurrentWeatherInfoUseCase
) : AndroidViewModel(application) {

    private val _weatherInfoStateFlow: MutableStateFlow<ResultState<HongKongLocationTemperature>> by lazy {
        MutableStateFlow(ResultState.OnInit())
    }
    val weatherInfoState: StateFlow<ResultState<HongKongLocationTemperature>> get() = _weatherInfoStateFlow

    private val _locationListStateFlow: MutableStateFlow<List<String>> by lazy {
        MutableStateFlow(listOf())
    }
    val locationListStateFlow: StateFlow<List<String>> get() = _locationListStateFlow

    private val _selectedLocationState: MutableStateFlow<String> by lazy {
        MutableStateFlow("")
    }
    val selectedLocationState: StateFlow<String> get() = _selectedLocationState

    @RequiresApi(Build.VERSION_CODES.O)
    val temperatureInfoStateFlow: StateFlow<TemperatureInfo> =
        weatherInfoState.combine(selectedLocationState) { state, selectedLocation ->
            when (state) {
                is ResultState.OnSuccess -> {

                    val data = state.data
                    val humidityData =
                        data.humidity.data.firstOrNull { humidityItem -> humidityItem.place == selectedLocation }
                    val rainfallData =
                        data.rainfall.data.firstOrNull { rainFallItem -> rainFallItem.place == selectedLocation }
                    val temperatureData =
                        data.temperature.data.firstOrNull { temperatureItem -> temperatureItem.place == selectedLocation }

                    TemperatureInfo(
                        dateString = todayDateString("yyyy-MM-dd"),
                        humidity = humidityData,
                        temperature = temperatureData,
                        rainFall = rainfallData,
                        warningMessage = data.warningMessage.firstOrNull() ?: ""
                    )
                }

                else -> {
                    TemperatureInfo.placeholder()
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, TemperatureInfo.placeholder())

    val loadingStateFlow: StateFlow<Boolean> = weatherInfoState.map { state ->
        state is ResultState.OnLoading
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        getCurrentWeatherInfo()
    }

    private fun getCurrentWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeatherInfoUseCase.getCurrentLocationInfo()
                .onInit {}
                .onLoading {
                    _weatherInfoStateFlow.value = ResultState.OnLoading()
                }
                .onSuccess { data ->
                    _weatherInfoStateFlow.value = ResultState.OnSuccess(data = data)
                    createLocationList(data.temperature.data)
                }
                .onError {
                    _weatherInfoStateFlow.value = ResultState.OnError(it)
                }
        }
    }

    private fun createLocationList(temperatureList: List<HumidityData>) {
        if (temperatureList.isEmpty()) return
        temperatureList.map {
            it.place
        }.distinct().let {
            _locationListStateFlow.value = it
            updateSelectedLocation(it[0])
        }
    }

    fun updateSelectedLocation(location: String) {
        _selectedLocationState.value = location
    }
}