package com.example.weatherapp

import com.example.weatherapp.data.CurrentConditions
import com.example.weatherapp.data.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query(value = "zip") zip: String = "55106",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appID: String = "ac9c193585c811bd2d1f3db2b7cb2deb"
    ): CurrentConditions

    @GET("/data/2.5/forecast/daily")
    suspend fun getForecastData(
        @Query(value = "zip") zip: String = "55106",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "cnt") count: Int = 16,
        @Query(value = "appid") appID: String = "ac9c193585c811bd2d1f3db2b7cb2deb"
    ): Forecast
}