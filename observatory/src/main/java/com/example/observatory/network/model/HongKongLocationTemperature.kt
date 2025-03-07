package com.example.observatory.network.model

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
    val uvindex: Uvindex?,
    val warningMessage: List<String> = emptyList()
){

    companion object{
        fun dummy(): HongKongLocationTemperature{
            return HongKongLocationTemperature(
                humidity = Humidity(
                    data = listOf(
                        HumidityData(
                            place = "Hong Kong Observatory",
                            unit = "%",
                            value = 80.0f
                        )
                    ),
                    recordTime = "2021-09-01T00:00:00"
                ),
                icon = listOf(1,2,3),
                iconUpdateTime = "2021-09-01T00:00:00",
                mintempFrom00To09 = "25.0",
                rainfall = Rainfall(
                    data = listOf(
                        RainFallData(
                            main = "Hong Kong Observatory",
                            max = 0.0f,
                            place = "Hong Kong Observatory",
                            unit = "mm"
                        )
                    ),
                    endTime = "2021-09-01T00:00:00",
                    startTime = "2021-09-01T00:00:00"
                ),
                rainfallFrom00To12 = "0.0",
                rainfallJanuaryToLastMonth = "0.0",
                rainfallLastMonth = "0.0",
                specialWxTips = listOf("1","2","3"),
                tcmessage = "1",
                temperature = Temperature(
                    data = listOf(
                        HumidityData(
                            place = "Hong Kong Observatory",
                            unit = "C",
                            value = 25.0f
                        )
                    ),
                    recordTime = "2021-09-01T00:00:00"
                ),
                updateTime = "2021-09-01T00:00:00",
                uvindex = Uvindex(
                    data = listOf(
                        UvindexData(
                            desc = "1",
                            place = "Hong Kong Observatory",
                            value = 1.0f
                        )
                    ),
                    recordDesc = "1"
                ),
                warningMessage = emptyList()
            )
        }
    }
}

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
    val data: List<UvindexData>?,
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