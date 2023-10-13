package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private var _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    fun loadWeatherInfo() {
        viewModelScope.launch {
            delay(500L)
            _state.value = WeatherState(
                isLoading = true,
                error = null
            )
            delay(1000L)
            when (val result = repository.fetchWeatherData(lat = state.value.lat, long = state.value.long)) {
                is Resource.Success -> {
                    _state.value = WeatherState(
                        weatherInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    _state.value = WeatherState(
                        weatherInfo = null,
                        isLoading = false,
                        error = "Could not load weather data. Make sure to enable internet!"
                    )
                }
            }
        }
    }
}