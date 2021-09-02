package com.iulian.iancu.memeweather.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iulian.iancu.memeweather.data.WeatherResult
import com.iulian.iancu.memeweather.databinding.WeatherItemBinding

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
            Mode.Current -> TODO()
            Mode.Daily -> TODO()
            Mode.Weekly -> TODO()
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