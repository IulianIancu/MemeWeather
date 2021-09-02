package com.iulian.iancu.memeweather.ui.main

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iulian.iancu.memeweather.LOST_GIF
import com.iulian.iancu.memeweather.data.WeatherRepository

class MainViewModel constructor(
    private val weatherRepository: WeatherRepository,
    private val geocoder: Geocoder
) : ViewModel() {
    private val _state = MutableLiveData(State(null))
    val state: LiveData<State> get() = _state

    // TODO: Implement the ViewModel
    fun findWeather(postcode: String) {
        val addresses: List<Address> = geocoder.getFromLocationName(postcode, 1)
        if (addresses.isNotEmpty()) {
            _state.postValue(_state.value?.copy(error = null))
        } else {
            _state.postValue(_state.value?.copy(error = Error.Geocoder))
        }
    }
}

data class State(
    val error: Error?
)

sealed class Error(val meme: String) {
    object Geocoder : Error(LOST_GIF)
}