package com.iulian.iancu.memeweather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iulian.iancu.memeweather.MainViewModelFactory
import com.iulian.iancu.memeweather.R
import com.iulian.iancu.memeweather.WeatherService
import com.iulian.iancu.memeweather.data.WeatherRepository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofitService = WeatherService.getInstance()
        val weatherRepository = WeatherRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(weatherRepository)
        ).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}