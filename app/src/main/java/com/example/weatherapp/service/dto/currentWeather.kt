package com.example.weatherapp.service.dto



import com.google.gson.annotations.SerializedName

data class currentWeather(
    @SerializedName("base")
    val base: String,
    @SerializedName("Clouds")
    val clouds: Clouds,
    val cod: Int,
    @SerializedName("Coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("Sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("Weather")
    val weather: List<Weather>,
    @SerializedName("Wind")
    val wind: Wind
) {
    data class Coord(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double
    )

    data class Weather(
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("id") val id: Int,
        @SerializedName("main")val main: String
    )

    data class Main(
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("temp") val temp: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("temp_min") val tempMin: Double
    )

    data class Wind(
        val deg: Int,
        val speed: Double
    )

    data class Clouds(
        @SerializedName("all") val all: Int
    )

    data class Sys(
        @SerializedName("country") val country: String,
        @SerializedName("id") val id: Int,
        @SerializedName("sunrise") val sunrise: Int,
        @SerializedName("sunset") val sunset: Int,
        @SerializedName("type") val type: Int
    )


}