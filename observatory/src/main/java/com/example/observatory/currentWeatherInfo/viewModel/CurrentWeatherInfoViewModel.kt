package com.example.observatory.currentWeatherInfo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.observatory.currentWeatherInfo.usecase.CurrentWeatherInfoUseCase
import com.example.observatory.network.model.HongKongLocationTemperature
import com.example.observatory.repository.ObservatoryRepository
import com.rc.base.model.stateModel.ResultState
import com.rc.base.model.stateModel.onError
import com.rc.base.model.stateModel.onInit
import com.rc.base.model.stateModel.onLoading
import com.rc.base.model.stateModel.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrentWeatherInfoViewModel(
    application: Application,
    private val currentWeatherInfoUseCase: CurrentWeatherInfoUseCase
) : AndroidViewModel(application) {

    private val _weatherInfoStateFlow: MutableStateFlow<ResultState<HongKongLocationTemperature>> by lazy {
        MutableStateFlow(ResultState.OnInit())
    }
    val weatherInfoState: StateFlow<ResultState<HongKongLocationTemperature>> get() = _weatherInfoStateFlow

    init {
        getCurrentWeatherInfo()
    }


    private fun getCurrentWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherInfoStateFlow.value = currentWeatherInfoUseCase.getCurrentLocationInfo()
                .onInit {}
                .onLoading {}
                .onSuccess {}
                .onError {}
        }
    }
}