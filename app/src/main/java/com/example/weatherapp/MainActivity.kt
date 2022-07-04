package com.example.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherapp.ui.LocationDetails
import com.example.weatherapp.ui.LocationNotAvailable
import com.example.weatherapp.ui.WeatherviewModel
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.PermissionStatus.*
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherviewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherappTheme {
                val permissionState= rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
                when(val status=permissionState.status){
                    Granted-> Weatherscreen(viewModel)
                    is Denied->{
                        if (status.shouldShowRationale){
                            LocationNotAvailable(onContinueClick = permissionState::launchPermissionRequest)
                        }else{
                            LocationDetails (onContinueClick = permissionState::launchPermissionRequest)
                        }
                    }
                }

            }
        }
    } }




