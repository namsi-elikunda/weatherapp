package com.example.weatherapp

import androidx.activity.compose.LocalActivityResultRegistryOwner.current
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.service.dto.CurrentWeather
import com.example.weatherapp.ui.WeatherviewModel
import com.example.weatherapp.ui.theme.WeatherappTheme



@Composable
fun Weatherscreen(viewModel:WeatherviewModel) {
    val current by viewModel.current.collectAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .width(550.dp)
                .height(500.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 10.dp,
        ) {
            Image(
                painter = painterResource(id = R.drawable.bluebackground),
                contentDescription = "Background", contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Day",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                    current?.let {
                        // Weather(weather =it)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Divider()
            Card(
                modifier = Modifier
                    .width(550.dp)
                    .height(280.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 10.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.blackbackground),
                    contentDescription = "black back",
                    contentScale = ContentScale.FillHeight
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Night",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )
                }

            }

        }


    }
}

@Composable
fun Weather(weather: CurrentWeather) {
    Box {
        Column(
            Modifier
                .padding(top = 48.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = Temperature(weather.main.temp), fontSize = 48.sp, color = Color.White)
            Text(text = weather.weather.first().main, fontSize = 28.sp, color = Color.White)
            Text(text = weather.name, fontSize = 18.sp, color = Color.White)
            Text(text = weather.name, fontSize = 18.sp, color = Color.White)


        }
    }

}
@Composable
fun Temperature(temp: Double): String {

}


@Preview(showBackground = true)
@Composable
fun Preview() {
    WeatherappTheme {
        Weatherscreen()
    }
}

