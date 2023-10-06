package com.example.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("hourly")
    val weatherDataDto: WeatherDataDto
)
