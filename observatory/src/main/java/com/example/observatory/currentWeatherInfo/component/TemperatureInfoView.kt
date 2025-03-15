package com.example.observatory.currentWeatherInfo.component

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.observatory.network.model.HumidityData
import com.example.observatory.network.model.RainFallData
import com.example.observatory.network.model.Temperature
import com.rc.base.util.getCustomColor
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
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
    fontSize = 20.sp,
    textAlign = TextAlign.Center
)


@Composable
fun TemperatureInfoView(modifier: Modifier = Modifier, data: TemperatureInfo) {

    val humidity = data.humidity
    val temperature = data.temperature
    val rainFall = data.rainFall

    Box(
        modifier = modifier
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = data.dateString,
                style = dataTextStyle
            )
            Spacer(modifier.weight(1f))

            WeatherInfoCardView(
                modifier = Modifier.align(Alignment.Start),
                label = "濕度",
                value = "${humidity?.value ?: "-"} %"
            )
            WeatherInfoCardView(
                modifier = Modifier.align(Alignment.End),
                label = "溫度",
                value = "${temperature?.value ?: "-"} ${temperature?.unit ?: "C"}"
            )
            WeatherInfoCardView(
                modifier = Modifier.align(Alignment.Start),
                label = "降雨量",
                value = "${rainFall?.max ?: "-"} ${rainFall?.unit ?: "mm"}"
            )
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0x80979797),
                        shape = RoundedCornerShape(36.dp)
                    )
                    .padding(12.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                    text = data.warningMessage.ifEmpty { "No warning message No warning message No warning message No warning message No warning message No warning message No warning message" },
                    style = dataTextStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun WeatherInfoCardView(modifier: Modifier = Modifier, label: String, value: String) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(12.dp),
        colors = CardDefaults.cardColors()
            .copy(containerColor = getCustomColor().observatorySkyBlue),
        shape = RoundedCornerShape(24f),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = dataTextStyle
            )
            Text(
                text = value,
                style = dataTextStyle
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherInfoCardView() {
    WeatherInfoCardView(label = "", value = "")
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