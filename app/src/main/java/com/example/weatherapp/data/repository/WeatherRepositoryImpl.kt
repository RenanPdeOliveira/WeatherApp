package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.WeatherInfo
import java.lang.Exception

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun fetchWeatherData(lat: String, long: String): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = weatherApi.fetchWeatherData(lat = lat, long = long).toWeatherInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error detected")
        }
    }
}