package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.remote.WeatherDto

interface WeatherRepository {

    suspend fun fetchWeatherData(lat: String, long: String): Resource<WeatherDto>
}