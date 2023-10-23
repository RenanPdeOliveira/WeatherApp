package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.cities.City
import com.example.weatherapp.domain.cities.cities
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private var _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    val cityState = City(city = state.value.city, lat = state.value.lat, long = state.value.long)

    private var _searchBar = MutableStateFlow("")
    var searchBar = _searchBar.asStateFlow()

    private var _listOfCities = MutableStateFlow(cities)
    val listOfCities = searchBar.combine(_listOfCities) { text, cities ->
        if (text.isBlank()) {
            cities
        } else {
            cities.filter {
                it.doesMatchSearch(text)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _listOfCities.value
    )

    fun loadWeatherInfo(city: City) {
        viewModelScope.launch {
            _state.update {
                WeatherState(
                    isLoading = true,
                    error = null
                )
            }
            delay(1000L)
            when (val result = repository.fetchWeatherData(lat = city.lat, long = city.long)) {
                is Resource.Success -> {
                    _state.update {
                        WeatherState(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null,
                            city = city.city,
                            lat = city.lat,
                            long = city.long
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        WeatherState(
                            weatherInfo = null,
                            isLoading = false,
                            error = "Could not load weather data. Make sure to enable internet!"
                        )
                    }
                }
            }
        }
    }
}