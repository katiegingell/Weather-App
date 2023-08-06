package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.data.ForecastListData
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

@Composable
fun ForecastScreen(viewModel: ForecastViewModel = hiltViewModel(), zip: String) {
    val forecastData = viewModel.forecast.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.validateZipCode(zip)
        viewModel.viewAppeared(zip)
    }
    LazyColumn {
        items(items = forecastData.value?.forecastList ?: listOf()) {
            DayForecastView(forecast = it)
        }
    }
}

@Composable
fun DayForecastView(forecast: ForecastListData) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherConditionIconForecast(url = forecast.iconUrl)
            Text(
                text = "${monthDayFormat(forecast.date.toString())}",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Temp: " + forecast.temp.day.roundToInt() + "°",
                    fontSize = 14.sp
                )
                Row {
                    Text(
                        text = "High: " + forecast.temp.max.toInt() + "°",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Low: " + forecast.temp.min.toInt() + "°",
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Sunrise: " + sunRiseSetFormat(forecast.sunrise.toString()),
                    fontSize = 14.sp
                )
                Text(
                    text = "Sunset: " + sunRiseSetFormat(forecast.sunset.toString()),
                    fontSize = 14.sp
                )
            }
        }

    }
}

fun monthDayFormat(date: String): String? {
    val formatter = SimpleDateFormat("MMM d")
    val daysDate = Date(date.toLong() * 1000)
    return formatter.format(daysDate)
}

fun sunRiseSetFormat(date: String): String? {
    val formatter = SimpleDateFormat("h:mm aa")
    val daysDate = Date(date.toLong() * 1000)
    return formatter.format(daysDate)
}