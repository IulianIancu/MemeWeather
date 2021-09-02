package com.iulian.iancu.memeweather.ui.main

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iulian.iancu.memeweather.IMPOSSIBLE_ERROR_GIF
import com.iulian.iancu.memeweather.LOST_GIF
import com.iulian.iancu.memeweather.NO_CONNECTION_GIF
import com.iulian.iancu.memeweather.UNKNOWN_ERROR_GIF
import com.iulian.iancu.memeweather.data.WeatherRepository
import com.iulian.iancu.memeweather.data.WeatherResult
import kotlinx.coroutines.*

class MainViewModel constructor(
    private val weatherRepository: WeatherRepository,
    private val geocoder: Geocoder
) : ViewModel() {
    private val _state = MutableLiveData(State(null, null))
    val state: LiveData<State> get() = _state

    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.postValue(_state.value?.copy(error = Error.Unknown))
    }

    fun findWeather(postcode: String) {
        if (postcode.isBlank()) {
            _state.postValue(_state.value?.copy(error = Error.Geocoder))
            return
        }
        val addresses: List<Address> = geocoder.getFromLocationName(postcode, 1)
        if (addresses.isNotEmpty()) {
            job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val address = addresses[0]
                val response = weatherRepository.getAllWeather(address.latitude, address.longitude)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _state.postValue(
                            _state.value?.copy(
                                error = null,
                                weather = response.body()
                            )
                        )
                    } else {
                        _state.postValue(_state.value?.copy(error = Error.Network))
                    }
                }
            }
        } else {
            _state.postValue(_state.value?.copy(error = Error.Geocoder))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

data class State(
    val error: Error?,
    val weather: WeatherResult?
)

sealed class Error(val meme: String) {
    object Geocoder : Error(LOST_GIF)
    object Network : Error(NO_CONNECTION_GIF)
    object Unknown : Error(UNKNOWN_ERROR_GIF)
    object Impossible : Error(IMPOSSIBLE_ERROR_GIF)
}