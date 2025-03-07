package com.example.observatory.currentWeatherInfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.observatory.network.model.HumidityData
import com.example.observatory.network.model.RainFallData
import com.example.observatory.network.model.Temperature
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class TemperatureInfo(
    val dateString: String,
    val humidity: HumidityData?,
    val temperature: HumidityData?,
    val rainFall: RainFallData?,
    val warningMessage: String
) {
    companion object {
        fun placeholder(): TemperatureInfo {
            return TemperatureInfo(
                dateString = "",
                humidity = HumidityData(
                    place = "Sample Place",
                    unit = "%",
                    value = 0.0f
                ),
                temperature = HumidityData(
                    place = "Sample Place",
                    unit = "C",
                    value = 0.0f
                ),
                rainFall = RainFallData(
                    main = "",
                    max = 0.0f,
                    place = "Sample Place",
                    unit = "mm"
                ),
                warningMessage = ""
            )
        }
    }
}

val dataTextStyle = TextStyle(
    color = Color.Black,
    fontSize = 20.sp
)


@Composable
fun TemperatureInfoView(modifier: Modifier = Modifier, data: TemperatureInfo) {

    val humidity = data.humidity
    val temperature = data.temperature
    val rainFall = data.rainFall

    Box(modifier = modifier.background(Color(0x80000000))) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier =Modifier.weight(1f),
                text = data.dateString,
                style = dataTextStyle
            )

            Spacer(modifier.weight(3f))

            Text(
                modifier =Modifier.weight(1f),
                text = data.warningMessage,
                style = dataTextStyle
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "濕度： ${humidity?.value ?: "-"} %",
                    style = dataTextStyle
                )
                Text(
                    "溫度： ${temperature?.value ?: "-"} ${temperature?.unit ?: "C"}",
                    style = dataTextStyle

                )
                Text(
                    "降雨量： ${rainFall?.max ?: "-"} ${rainFall?.unit ?: "mm"}",
                    style = dataTextStyle
                )

            }


        }
    }
}


@Preview
@Composable
private fun PreviewTemperatureInfoView() {
    val sampleData = TemperatureInfo(
        dateString = "2025-03-06",
        humidity = HumidityData(
            place = "Sample Place",
            unit = "%",
            value = 60.0f
        ),
        temperature = HumidityData(
            place = "Sample Place",
            unit = "C",
            value = 25.0f
        ),
        rainFall = RainFallData(
            main = "Sample Main",
            max = 10.0f,
            place = "Sample Place",
            unit = "mm"
        ),
        warningMessage = "Sample Warning"
    )
    TemperatureInfoView(data = sampleData)

}