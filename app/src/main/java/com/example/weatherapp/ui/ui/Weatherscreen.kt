package com.example.weatherapp.ui.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.service.dto.CurrentWeather
import com.example.weatherapp.service.dto.FullWeather
import kotlin.math.roundToInt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.R


@Composable
fun Weatherscreen(viewModel: WeatherviewModel) {
    //var current by remember { mutableStateOf("") }
   // var forecast by remember { mutableStateOf("") }
   // var current: WeatherviewModel by remember { mutableStateOf(null)}
    //var forecast: WeatherviewModel by remember { mutableStateOf(null)}
//   val current by viewModel.current.collectAsState(initial = null)
  //  val forecast by viewModel.forecast.collectAsState(initial = null)
    val current: CurrentWeather? by viewModel.current.collectAsState()
    val forecast: State<FullWeather?> =viewModel.forecast.collectAsState(null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){Card(
        modifier = Modifier
            .width(550.dp)
            .height(420.dp),
        backgroundColor = (Color.Blue),
        contentColor = Color.White,
        elevation = 10.dp
    ) {
        current?.let {  weatherView(weather = it)
            temperatureView(it)
            Divider(color = Color.White)}}
        Card(
            modifier = Modifier
                .width(550.dp)
                .height(290.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = (Color.DarkGray),
            contentColor = Color.White,
            elevation = 10.dp
        ){
            forecast.value?.let { currentDailyForecast(it) }

        }

    }
    }

@Composable
fun weatherView(weather: CurrentWeather) {
    Box {
        Image(
            painter = painterResource(id = weather.background()),
            contentDescription = "Background",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Column(
            Modifier
                .padding(top = 48.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weather.weather.first().main, fontSize = 28.sp, color = Color.White)
            Text(text = temperature(weather.main.temp), fontSize = 48.sp, color = Color.White)
            Text(text = weather.name, fontSize = 18.sp, color = Color.White)
            Text(text = weather.name, fontSize = 18.sp, color = Color.White)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = humidity(weather.main.humidity), fontSize = 28.sp, color = Color.White)
            Text(text = wind(weather.wind.speed), fontSize = 28.sp, color = Color.White)
            Text(text = precipitation(weather.visibility), fontSize = 28.sp, color = Color.White)
        }
    }

}

private fun CurrentWeather.background(): Int {
    val conditions = weather.first().main
    return when {
        conditions.contains("cloud", ignoreCase = true) -> R.drawable.cloudy
        conditions.contains("rain", ignoreCase = true) -> R.drawable.rain
        else -> R.drawable.sunny
    }

}

@Composable
fun temperatureView(weather: CurrentWeather) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = temperature(weather.main.temp),
                fontSize = 18.sp,
                color = Color.White
            )
            Text(text = stringResource(R.string.Temperature), color = Color.White)
        }

    }
}
@Composable
private fun wind(speed: Double): String {
    return stringResource(id = R.string.wind, speed)

}

@Composable
private fun precipitation(visibility: Int): String {
    return stringResource(id = R.string.precipitation, visibility)

}

@Composable
private fun humidity(humidity: Int): String {
    return stringResource(id = R.string.humidity, humidity)
}

@Composable
fun temperature(temp: Double): String {
    return stringResource(id = R.string.temperature_degrees, temp.roundToInt())
}

@Composable
fun currentDailyForecast(forecast: FullWeather) {
    Box {
        Column(
            Modifier
                .padding(top = 48.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = sunset(forecast.current[0].sunset), fontSize = 18.sp, color = Color.White)
            Text(text = nightTemp (forecast.current[1].temp.night), fontSize = 18.sp, color = Color.White)


        }
    }


}

@Composable
fun sunset(sunset: Int): String {
    return stringResource(id = R.string.Sunset, sunset)

}

@Composable
fun nightTemp(temp: Double): String {
    return stringResource(id = R.string.NightTemp, temp)

}









