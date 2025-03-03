package com.example.observatory.network

import com.example.observatory.network.model.HongKongLocationTemperature
import com.rc.base.network.NetworkResponse
import retrofit2.http.GET

typealias ObservatoryBaseResponse<Data, Error> = NetworkResponse<Data, Error>
typealias ObservatoryResponse<Data> = ObservatoryBaseResponse<Data, BaseErrorResponse>

interface ObservatoryApiEndPoint {

    @GET("weatherAPI/opendata/weather.php?dataType=rhrread&lang=tc")
    suspend fun getCurrentWeather():ObservatoryResponse<HongKongLocationTemperature>
    
}