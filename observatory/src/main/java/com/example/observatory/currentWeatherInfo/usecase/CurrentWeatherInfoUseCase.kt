package com.example.observatory.currentWeatherInfo.usecase

import com.example.observatory.network.model.HongKongLocationTemperature
import com.rc.base.model.stateModel.ResultState

interface CurrentLocationInfoUseCase {

    suspend fun getCurrentLocationInfo(): ResultState<HongKongLocationTemperature>
}