package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.service.WeatherRepository
import com.example.weatherapp.service.dto.CurrentWeather
import com.example.weatherapp.service.dto.FullWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class WeatherviewModel @Inject constructor(repository:WeatherRepository) :ViewModel() {
    @SuppressLint("MissingPermission")
    val current: Flow<CurrentWeather> = repository.getCurrentWeather()
    @SuppressLint("MissingPermission")
    val forecast: Flow<FullWeather> = repository.getFullWeather()

}

