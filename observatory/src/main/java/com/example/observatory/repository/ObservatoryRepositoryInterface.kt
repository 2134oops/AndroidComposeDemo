package com.example.observatory.repository

import com.example.observatory.network.ObservatoryResponse
import com.example.observatory.network.model.HongKongLocationTemperature

interface ObservatoryRepositoryInterface {

    suspend fun getCurrentWeatherInfo(): ObservatoryResponse<HongKongLocationTemperature>
}