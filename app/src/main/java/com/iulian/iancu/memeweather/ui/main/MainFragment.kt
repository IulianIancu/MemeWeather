package com.iulian.iancu.memeweather.ui.main

import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.iulian.iancu.memeweather.HELLO_GIF
import com.iulian.iancu.memeweather.MainViewModelFactory
import com.iulian.iancu.memeweather.R
import com.iulian.iancu.memeweather.WeatherService
import com.iulian.iancu.memeweather.data.WeatherRepository
import com.iulian.iancu.memeweather.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofitService = WeatherService.getInstance()
        val weatherRepository = WeatherRepository(retrofitService)
        val geocoder = Geocoder(requireContext())
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(weatherRepository, geocoder)
        ).get(MainViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, ::onStateChange)

        Glide.with(this).load(HELLO_GIF).into(binding.topImage)
        binding.submit.setOnClickListener {
            viewModel.findWeather(binding.postcodeInput.text.toString())
        }
    }

    private fun onStateChange(state: State?) {
        if (state == null) return
        when (val error = state.error) {
            Error.Geocoder -> {
                binding.mainText.setText(R.string.error_no_postcode)
                Glide.with(this).load(error.meme).into(binding.topImage)
            }
            null -> {
                binding.mainText.setText(R.string.intro_spiel)
                Glide.with(this).load(HELLO_GIF).into(binding.topImage)
            }
        }
    }

}