package com.example.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

sealed class WeatherType(
    val description: String,
    @DrawableRes val icon: Int
) {
    object ClearSky : WeatherType(
        description = "Clear sky",
        icon = R.drawable.ic_sunny
    )
    object MainlyClear : WeatherType(
        description = "Mainly clear",
        icon = R.drawable.ic_sunnycloudy
    )
    object PartlyCloudy : WeatherType(
        description = "Partly clear",
        icon = R.drawable.ic_sunnycloudy
    )
    object Overcast : WeatherType(
        description = "Overcast",
        icon = R.drawable.ic_cloudy
    )
    object Foggy : WeatherType(
        description = "Foggy",
        icon = R.drawable.ic_verycloudy
    )
    object DepositingRimeFog : WeatherType(
        description = "Depositing rime fog",
        icon = R.drawable.ic_verycloudy
    )
    object LightDrizzle : WeatherType(
        description = "Light drizzle",
        icon = R.drawable.ic_slightrainshower
    )
    object ModerateDrizzle : WeatherType(
        description = "Moderate drizzle",
        icon = R.drawable.ic_rainshower
    )
    object DenseDrizzle : WeatherType(
        description = "Dense drizzle",
        icon = R.drawable.ic_rainshower
    )
    object LightFreezingDrizzle : WeatherType(
        description = "Light freezing drizzle",
        icon = R.drawable.ic_snowyrainy
    )
    object DenseFreezingDrizzle : WeatherType(
        description = "Dense freezing drizzle",
        icon = R.drawable.ic_snowyrainy
    )
    object SlightRain : WeatherType(
        description = "Slight rain",
        icon = R.drawable.ic_rainy
    )
    object ModerateRain : WeatherType(
        description = "Moderate rain",
        icon = R.drawable.ic_rainy
    )
    object HeavyRain : WeatherType(
        description = "Heavy rain",
        icon = R.drawable.ic_rainy
    )
    object HeavyFreezingRain : WeatherType(
        description = "Heavy freezing rain",
        icon = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall : WeatherType(
        description = "Slight snow fall",
        icon = R.drawable.ic_snowy
    )
    object ModerateSnowFall : WeatherType(
        description = "Moderate snow fall",
        icon = R.drawable.ic_heavysnow
    )
    object HeavySnowFall : WeatherType(
        description = "Heavy snow fall",
        icon = R.drawable.ic_heavysnow
    )
    object SnowGrains : WeatherType(
        description = "Snow grains",
        icon = R.drawable.ic_heavysnow
    )
    object SlightRainShowers : WeatherType(
        description = "Slight rain showers",
        icon = R.drawable.ic_slightrainshower
    )
    object ModerateRainShowers : WeatherType(
        description = "Moderate rain showers",
        icon = R.drawable.ic_rainshower
    )
    object ViolentRainShowers : WeatherType(
        description = "Violent rain showers",
        icon = R.drawable.ic_rainshower
    )
    object SlightSnowShowers : WeatherType(
        description = "Slight snow showers",
        icon = R.drawable.ic_snowy
    )
    object ModerateSnowShowers : WeatherType(
        description = "Moderate snow showers",
        icon = R.drawable.ic_snowy
    )
    object HeavySnowShowers : WeatherType(
        description = "Heavy snow showers",
        icon = R.drawable.ic_snowy
    )
    object SlightThunderstorm : WeatherType(
        description = "Slight thunderstorm",
        icon = R.drawable.ic_thunder
    )
    object ModerateThunderstorm : WeatherType(
        description = "Moderate thunderstorm",
        icon = R.drawable.ic_rainythunder
    )
    object HeavyThunderstorm : WeatherType(
        description = "Heavy thunderstorm",
        icon = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightThunderstorm
                99 -> HeavyThunderstorm
                else -> ClearSky
            }
        }
    }
}