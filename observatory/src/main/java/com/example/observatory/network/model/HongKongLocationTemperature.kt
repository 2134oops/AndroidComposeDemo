package com.example.observatory.network.model

import com.google.gson.annotations.SerializedName


data class HongKongLocationTemperature(
    val humidity: Humidity,
    val icon: List<Int>,
    val iconUpdateTime: String,
    val mintempFrom00To09: String,
    val rainfall: Rainfall,
    val rainfallFrom00To12: String,
    val rainfallJanuaryToLastMonth: String,
    val rainfallLastMonth: String,
    val specialWxTips: List<String>,
    val tcmessage: String,
    val temperature: Temperature,
    val updateTime: String,
    @SerializedName("uvindex")
    val uvindex: Uvindex?,
    val warningMessage: String
)

data class Humidity(
    val data: List<HumidityData>,
    val recordTime: String
)

data class Rainfall(
    val data: List<RainFallData>,
    val endTime: String,
    val startTime: String
)

data class Temperature(
    val data: List<HumidityData>,
    val recordTime: String
)

data class Uvindex(
    @SerializedName("data")
    val data: List<UvindexData>,
    val recordDesc: String
)

data class HumidityData(
    val place: String,
    val unit: String,
    val value: Float
)

data class RainFallData(
    val main: String,
    val max: Float,
    val place: String,
    val unit: String
)

data class UvindexData(
    val desc: String,
    val place: String,
    val value: Float
)