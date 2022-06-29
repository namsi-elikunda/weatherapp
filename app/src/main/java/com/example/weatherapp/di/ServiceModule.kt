package com.example.weatherapp.service

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

import javax.inject.Singleton

object ServiceModule {
    @Provides
    @Singleton

    fun provideOpenWeatherService():OpenWeatherService{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/3.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}
