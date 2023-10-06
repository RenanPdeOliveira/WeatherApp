package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun fetchWeatherData(lat: String, long: String): Resource<WeatherDto> {
        TODO("Not yet implemented")
    }
}