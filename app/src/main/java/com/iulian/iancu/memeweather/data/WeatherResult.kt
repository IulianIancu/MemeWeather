package com.iulian.iancu.memeweather.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResult(
    @Expose
    @SerializedName("lat")
    val lat: Double,
    @Expose
    @SerializedName("lon")
    val lon: Double,
    @Expose
    @SerializedName("timezone")
    val timezone: String,
    @Expose
    @SerializedName("timezone_offset")
    var timezone_offset: Int,
    @Expose
    @SerializedName("current")
    var current: Current,
    @Expose
    @SerializedName("minutely")
    var minutely: List<Minutely>,
    @Expose
    @SerializedName("hourly")
    var hourly: List<Hourly>,
    @Expose
    @SerializedName("daily")
    var daily: List<Daily>
)

data class Daily(
    @Expose
    @SerializedName("dt")
    var dt: Int,
    @Expose
    @SerializedName("sunrise")
    var sunrise: Int,
    @Expose
    @SerializedName("sunset")
    var sunset: Int,
    @Expose
    @SerializedName("moonrise")
    var moonrise: Int,
    @Expose
    @SerializedName("moonset")
    var moonset: Int,
    @Expose
    @SerializedName("moon_phase")
    val moon_phase: Double,
    @Expose
    @SerializedName("temp")
    var temp: Temp,
    @Expose
    @SerializedName("feels_like")
    var feels_like: Feels_like,
    @Expose
    @SerializedName("pressure")
    var pressure: Int,
    @Expose
    @SerializedName("humidity")
    var humidity: Int,
    @Expose
    @SerializedName("dew_point")
    val dew_point: Double,
    @Expose
    @SerializedName("wind_speed")
    val wind_speed: Double,
    @Expose
    @SerializedName("wind_deg")
    var wind_deg: Int,
    @Expose
    @SerializedName("wind_gust")
    val wind_gust: Double,
    @Expose
    @SerializedName("weather")
    var weather: List<Weather>,
    @Expose
    @SerializedName("clouds")
    var clouds: Int,
    @Expose
    @SerializedName("pop")
    val pop: Double,
    @Expose
    @SerializedName("rain")
    val rain: Double,
    @Expose
    @SerializedName("uvi")
    var uvi: Double
)

data class Weather(
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("main")
    val main: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("icon")
    val icon: String
)

data class Feels_like(
    @Expose
    @SerializedName("day")
    val day: Double,
    @Expose
    @SerializedName("night")
    val night: Double,
    @Expose
    @SerializedName("eve")
    val eve: Double,
    @Expose
    @SerializedName("morn")
    val morn: Double
)

data class Temp(
    @Expose
    @SerializedName("day")
    val day: Double,
    @Expose
    @SerializedName("min")
    val min: Double,
    @Expose
    @SerializedName("max")
    val max: Double,
    @Expose
    @SerializedName("night")
    val night: Double,
    @Expose
    @SerializedName("eve")
    val eve: Double,
    @Expose
    @SerializedName("morn")
    val morn: Double
)

data class Hourly(
    @Expose
    @SerializedName("dt")
    var dt: Int,
    @Expose
    @SerializedName("temp")
    val temp: Double,
    @Expose
    @SerializedName("feels_like")
    val feels_like: Double,
    @Expose
    @SerializedName("pressure")
    var pressure: Int,
    @Expose
    @SerializedName("humidity")
    var humidity: Int,
    @Expose
    @SerializedName("dew_point")
    val dew_point: Double,
    @Expose
    @SerializedName("uvi")
    val uvi: Double,
    @Expose
    @SerializedName("clouds")
    var clouds: Int,
    @Expose
    @SerializedName("visibility")
    var visibility: Int,
    @Expose
    @SerializedName("wind_speed")
    val wind_speed: Double,
    @Expose
    @SerializedName("wind_deg")
    var wind_deg: Int,
    @Expose
    @SerializedName("wind_gust")
    val wind_gust: Double,
    @Expose
    @SerializedName("weather")
    var weather: List<Weather>,
    @Expose
    @SerializedName("pop")
    var pop: Int
)

data class Minutely(
    @Expose
    @SerializedName("dt")
    var dt: Int,
    @Expose
    @SerializedName("precipitation")
    var precipitation: Int
)

data class Current(
    @Expose
    @SerializedName("dt")
    var dt: Int,
    @Expose
    @SerializedName("sunrise")
    var sunrise: Int,
    @Expose
    @SerializedName("sunset")
    var sunset: Int,
    @Expose
    @SerializedName("temp")
    val temp: Double,
    @Expose
    @SerializedName("feels_like")
    val feels_like: Double,
    @Expose
    @SerializedName("pressure")
    var pressure: Int,
    @Expose
    @SerializedName("humidity")
    var humidity: Int,
    @Expose
    @SerializedName("dew_point")
    val dew_point: Double,
    @Expose
    @SerializedName("uvi")
    val uvi: Double,
    @Expose
    @SerializedName("clouds")
    var clouds: Int,
    @Expose
    @SerializedName("visibility")
    var visibility: Int,
    @Expose
    @SerializedName("wind_speed")
    val wind_speed: Double,
    @Expose
    @SerializedName("wind_deg")
    var wind_deg: Int,
    @Expose
    @SerializedName("wind_gust")
    val wind_gust: Double,
    @Expose
    @SerializedName("weather")
    var weather: List<Weather>
)

