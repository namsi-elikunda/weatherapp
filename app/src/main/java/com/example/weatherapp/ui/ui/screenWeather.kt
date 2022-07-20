package com.example.weatherapp.ui.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.service.dto.FullWeather
import com.example.weatherapp.service.dto.currentWeather
import com.example.weatherapp.ui.theme.CloudyBlue
import com.example.weatherapp.ui.theme.RainyGrey
import com.example.weatherapp.ui.theme.SunnyYellow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.roundToInt

@Composable
fun screenWeather(viewModel: WeatherviewModel) {

    val current: currentWeather? by viewModel.current.collectAsState()
    val forecast: State<FullWeather?> = viewModel.forecast.collectAsState(null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(440.dp),
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            elevation = 10.dp,
            shape = RoundedCornerShape(23.dp),

            ) {
            current?.let {
                weatherView(current = it)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(bottom = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Card(
                modifier = Modifier
                    .width(350.dp)
                    .height(200.dp),
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 10.dp,
                shape = RoundedCornerShape(23.dp)

            ) {

                currentDailyForecast(forecast)


            }

        }

    }

}
@Composable
fun weatherView(current: currentWeather) {
    Box {
        Row(
            Modifier
                .padding(top = 28.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = current.background()),
                contentDescription = "Background",
                modifier = Modifier.size(150.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            Modifier
                .padding(top = 48.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = temperature(current.main.temp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 38.sp
            )
            Text(
                text = current.sys.country,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 36.sp
            )
        }
        Row(
            Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(text = WindNow(current.wind.speed), fontSize = 18.sp, color = Color.White)
            Text(text = Humidity(current.main.humidity), fontSize = 15.sp, color = Color.White)
            Text(text = Precipitation(current.main.seaLevel), fontSize = 15.sp, color = Color.White)
        }

    }

}


@Composable
fun Precipitation(seaLevel: Int): String {
    return stringResource(id = R.string.precipitation, seaLevel)

}

@Composable
fun Humidity(humidity: Int): String {
    return stringResource(id = R.string.humidity, humidity)


}

@Composable
fun WindNow(speed: Double): String {
    return stringResource(id = R.string.windNow, speed.roundToInt())

}

@Composable
fun temperature(temp: Double): String {
    return stringResource(id = R.string.temperature_degrees, temp.roundToInt())

}
@DrawableRes
private fun currentWeather.background(): Int {
    val conditions = weather.first().main
    return when {
        conditions.contains("cloud", ignoreCase = true) -> R.drawable.fog
        conditions.contains("rain", ignoreCase = true) -> R.drawable.day_rain
        else -> R.drawable.day_clear
    }
}



@Composable
fun currentDailyForecast(forecast: State<FullWeather?>) {
    Box {
        Row(
            Modifier
                .padding(top = 28.dp)
                .size(329.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Night", fontSize = 18.sp, color = Color.White)
            Image(
                painter = painterResource(R.drawable.night_clear),
                contentDescription = "Background",
                modifier = Modifier.size(24.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            Modifier
                .padding(top = 18.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =Temperature (forecast.value!!.current.temp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp
            )


        }
    }

}

@Composable
fun Temperature(temp: Double):String {
    return stringResource(id = R.string.temperature_degrees,temp.roundToInt())

}

