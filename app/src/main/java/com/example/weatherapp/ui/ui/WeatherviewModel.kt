package com.example.weatherapp.ui.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.service.WeatherRepository
import com.example.weatherapp.service.dto.CurrentWeather
import com.example.weatherapp.service.dto.FullWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
@HiltViewModel
@SuppressLint("MissingPermission")
class WeatherviewModel @Inject constructor(repository: WeatherRepository) :ViewModel() {
    val current: Flow<CurrentWeather> =repository.getCurrentWeather()
    val forecast: Flow<FullWeather> =repository.getcurrentDailyForecast()

}

