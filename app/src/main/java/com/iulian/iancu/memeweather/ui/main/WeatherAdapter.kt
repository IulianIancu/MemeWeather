package com.iulian.iancu.memeweather.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iulian.iancu.memeweather.data.WeatherResult
import com.iulian.iancu.memeweather.databinding.WeatherItemBinding
import com.iulian.iancu.memeweather.getFormattedDateFromUTC
import com.iulian.iancu.memeweather.getWatherIconFromCode

@SuppressLint("NotifyDataSetChanged")
class WeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>() {
    private var weatherResult: WeatherResult? = null
    private var mode: Mode = Mode.Current

    fun setWeatherData(weatherResult: WeatherResult) {
        this.weatherResult = weatherResult
        notifyDataSetChanged()
    }

    fun setMode(mode: Mode) {
        this.mode = mode
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        when (mode) {
            Mode.Current -> {
                holder.binding.apply {
                    weatherResult?.current?.temp?.let {
                        weatherDescription.text = "Temperature is $it °C"
                    }
                    weatherResult?.current?.dt?.let {
                        weatherSubtext.text = getFormattedDateFromUTC(it)
                    }

                    val weather = weatherResult?.current?.weather?.getOrNull(0)
                    if (weather != null) {
                        weatherTitle.text = weather.description
                        Glide.with(this.weatherImage).load(getWatherIconFromCode(weather.id))
                            .into(weatherImage)
                    }
                }
            }
            Mode.Daily -> {
                holder.binding.apply {
                    val data = weatherResult?.hourly?.get(position)
                    data?.temp?.let {
                        weatherDescription.text = "Temperature is $it °C"
                    }
                    data?.dt?.let {
                        weatherSubtext.text = getFormattedDateFromUTC(it)
                    }

                    val weather = data?.weather?.getOrNull(0)
                    if (weather != null) {
                        weatherTitle.text = weather.description
                        Glide.with(this.weatherImage).load(getWatherIconFromCode(weather.id))
                            .into(weatherImage)
                    }
                }
            }
            Mode.Weekly -> {
                holder.binding.apply {
                    val data = weatherResult?.daily?.get(position)
                    data?.temp?.let {
                        weatherDescription.text = "Temperature is ${it.day} °C"
                    }
                    data?.dt?.let {
                        weatherSubtext.text = getFormattedDateFromUTC(it)
                    }

                    val weather = data?.weather?.getOrNull(0)
                    if (weather != null) {
                        weatherTitle.text = weather.description
                        Glide.with(this.weatherImage).load(getWatherIconFromCode(weather.id))
                            .into(weatherImage)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (weatherResult == null) return 0

        return when (mode) {
            Mode.Current -> if (weatherResult?.current != null) 1 else 0
            Mode.Daily -> weatherResult?.hourly?.size ?: 0
            Mode.Weekly -> weatherResult?.daily?.size ?: 0
        }
    }

}

sealed class Mode() {
    object Current : Mode()
    object Daily : Mode()
    object Weekly : Mode()
}

class WeatherViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)