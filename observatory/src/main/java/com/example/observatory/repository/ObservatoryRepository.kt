package com.example.observatory.repository

import com.example.observatory.network.ObservatoryApiEndPoint
import com.example.observatory.network.ObservatoryResponse
import com.example.observatory.network.model.HongKongLocationTemperature

class ObservatoryRepository(
    private val observatoryApiEndPoint: ObservatoryApiEndPoint
) : ObservatoryRepositoryInterface {
    override suspend fun getCurrentWeatherInfo(): ObservatoryResponse<HongKongLocationTemperature> =
        observatoryApiEndPoint.getCurrentWeather()

}