package com.iulian.iancu.memeweather.data

import com.iulian.iancu.memeweather.WeatherService

class WeatherRepository constructor(private val retrofitService: WeatherService) {

    suspend fun getAllWeather(lat: Double, lon: Double) =
        retrofitService.getWeatherForLocation(lat, lon)
}