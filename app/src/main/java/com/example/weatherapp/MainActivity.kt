package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.example.weatherapp.ui.ui.LocationNotAvailable
import com.example.weatherapp.ui.ui.WeatherviewModel
import com.example.weatherapp.ui.ui.screenWeather
import com.example.weatherapp.ui.ui.utils.MyUpdatedLocationArgs
import com.example.weatherapp.ui.ui.utils.rememberMyUpdatedLocation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherviewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherappTheme {
                var shouldRequestLocationPermission by remember {
                    mutableStateOf(false)
                }
                val args = MyUpdatedLocationArgs(
                    onMyUpdatedLocationChanged = viewModel::setLocation,
                    shouldRequestPermission = shouldRequestLocationPermission,
                ) {

                }
                val myUpdatedLocation = rememberMyUpdatedLocation(args = args)
                if (myUpdatedLocation.isLocationPermissionGranted) {
                    screenWeather(viewModel)
                } else {
                    LocationNotAvailable(onContinueClick = {
                        shouldRequestLocationPermission = true
                    })
                }
                /*
                val permissionState =
                    rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
                when (val status = permissionState.status) {
                    Granted -> Weatherscreen(viewModel)
                    is Denied -> {
                        if (status.shouldShowRationale) {
                            LocationNotAvailable(onContinueClick = permissionState::launchPermissionRequest)
                        } else {
                            LocationDetails(onContinueClick = permissionState::launchPermissionRequest)
                        }
                    }
                }*/

            }
        }
    }
}




