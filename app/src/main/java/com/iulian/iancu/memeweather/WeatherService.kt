package com.iulian.iancu.memeweather

import com.iulian.iancu.memeweather.data.WeatherResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun getWeatherForLocation(
        @Query("lat") lat: Double,
        @Query("long") long: Double,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "alerts",
    ): Response<WeatherResult>

    companion object {
        var retrofitService: WeatherService? = null
        fun getInstance(): WeatherService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(create())
                    .build()
                retrofitService = retrofit.create(WeatherService::class.java)
            }
            return retrofitService!!
        }

    }
}