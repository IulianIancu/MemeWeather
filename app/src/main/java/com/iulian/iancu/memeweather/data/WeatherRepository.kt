package com.iulian.iancu.memeweather.data

import com.iulian.iancu.memeweather.WeatherService

class WeatherRepository constructor(private val retrofitService: WeatherService) {

    suspend fun getAllMovies(lat: Double, long: Double) =
        retrofitService.getWeatherForLocation(lat, long)
}