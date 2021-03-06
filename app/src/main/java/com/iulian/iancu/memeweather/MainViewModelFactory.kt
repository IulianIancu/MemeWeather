package com.iulian.iancu.memeweather

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iulian.iancu.memeweather.data.WeatherRepository
import com.iulian.iancu.memeweather.ui.main.MainViewModel

class MainViewModelFactory constructor(
    private val repository: WeatherRepository,
    private val geocoder: Geocoder
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository, geocoder) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}