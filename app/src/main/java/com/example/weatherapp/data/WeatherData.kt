package com.example.weatherapp.data

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val iconName: String
)

data class Temperature(
    @Json(name = "day") val day: Float,
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)

data class CurrentWeather(
    @Json(name = "temp") val temp: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Int
)

data class CurrentConditions(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherConditions: List<WeatherData>,
    @Json(name = "main") val currentWeather: CurrentWeather
) {
    val iconUrl: String
        get() = "https://openweathermap.org/img/wn/${weatherConditions.firstOrNull()?.iconName}@2x.png"
}

data class ForecastListData(
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: Temperature,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "weather") val weatherConditions: List<WeatherData>
) {
    val iconUrl: String
        get() = "https://openweathermap.org/img/wn/${weatherConditions.firstOrNull()?.iconName}@2x.png"
}

data class Forecast(
    @Json(name = "cnt") val count: Int,
    @Json(name = "list") val forecastList: List<ForecastListData>
)