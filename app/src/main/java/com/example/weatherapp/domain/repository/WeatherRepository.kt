package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun fetchWeatherData(lat: String, long: String): Resource<WeatherInfo>
}