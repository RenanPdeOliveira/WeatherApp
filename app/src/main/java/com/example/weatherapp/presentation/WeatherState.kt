package com.example.weatherapp.presentation

import com.example.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val city: String = "São Paulo",
    val lat: String = "-23.55",
    val long: String = "-46.6333"
)
