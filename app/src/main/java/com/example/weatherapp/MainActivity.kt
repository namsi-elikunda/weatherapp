package com.example.weatherapp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.weatherapp.service.dto.CurrentWeather
import com.example.weatherapp.ui.LocationDetails
import com.example.weatherapp.ui.LocationNotAvailable
import com.example.weatherapp.ui.WeatherviewModel
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.PermissionStatus.*
import com.google.accompanist.permissions.rememberPermissionState
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {
    private val WeatherviewModel: WeatherviewModel by viewModels()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherappTheme {
                val permissionState =
                    rememberPermissionState(permission =android.Manifest.permission.ACCESS_FINE_LOCATION)
                when (val status = permissionState.status) {
                    Granted -> Weatherscreen(viewModel = WeatherviewModel)
                    is Denied -> {
                        if (status.shouldShowRationale) {
                            LocationNotAvailable(onContinueClick = permissionState::launchPermissionRequest)
                        } else {
                            LocationDetails(onContinueClick = permissionState::launchPermissionRequest)
                        }


                        // Weatherscreen(WeatherviewModel)
                    }
                }
            }
        }
    } }




