package com.example.observatory.currentWeatherInfo.usecase

import com.example.observatory.network.model.HongKongLocationTemperature
import com.example.observatory.repository.ObservatoryRepository
import com.rc.base.model.stateModel.ResultState
import com.rc.base.util.convertToResultState

class CurrentWeatherInfoUseCaseImpl(val repository: ObservatoryRepository) :
    CurrentWeatherInfoUseCase {
    override suspend fun getCurrentLocationInfo(): ResultState<HongKongLocationTemperature> {
        return repository.getCurrentWeatherInfo().convertToResultState {
            it
        }
    }
}