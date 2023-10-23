package com.example.weatherapp.presentation

import com.example.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    var city: String = "São Paulo",
    var lat: String = "-23.55",
    var long: String = "-46.6333"
)
