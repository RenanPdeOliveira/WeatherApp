package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val adapter = WeatherItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadWeatherInfo()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (state.isLoading) {
                        binding.linearLayoutContent.visibility = View.GONE
                        binding.progressBarLoading.visibility = View.VISIBLE
                    }
                    if (!state.isLoading) {
                        binding.linearLayoutContent.visibility = View.VISIBLE
                        binding.progressBarLoading.visibility = View.GONE
                    }
                    state.error?.let {
                        binding.linearLayoutContent.visibility = View.GONE
                        binding.progressBarLoading.visibility = View.GONE
                        binding.linearLayoutEmptyState.visibility = View.VISIBLE
                        Snackbar.make(binding.root, "There is no internet connection!", Snackbar.LENGTH_SHORT).show()
                    }
                    state.weatherInfo?.currentlyWeatherData?.let { data ->
                        binding.textViewCityName.text = state.city
                        binding.textViewHour.text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                        binding.imageViewIcon.load(data.weatherType.icon)
                        binding.textViewTemperature.text = "${data.temperature}ºC"
                        binding.textViewDailyDescription.text = data.weatherType.description
                        binding.textViewPressureNum.text = "${data.pressure}hpa"
                        binding.textViewHumidityNum.text = "${data.humidity}%"
                        binding.textViewWindSpeedNum.text = "${data.windSpeed}km/h"
                    }
                    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { list ->
                        binding.recyclerView.adapter = adapter
                        adapter.submitList(list)
                    }
                    binding.buttonTryAgain.setOnClickListener {
                        binding.linearLayoutEmptyState.visibility = View.GONE
                        viewModel.loadWeatherInfo()
                    }
                }
            }
        }

    }
}