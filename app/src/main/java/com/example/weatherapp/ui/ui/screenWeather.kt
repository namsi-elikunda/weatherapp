package com.example.weatherapp.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner.current
import com.example.weatherapp.R

@Composable
fun screenWeather(viewModel: WeatherviewModel) {

  //    val current: currentWeather? by viewModel.current.collectAsState()


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
                current?.let {
                    currentDailyForecast()

                }
            }

        }

    }

}
@Composable
fun weatherView(current: ViewModelStoreOwner) {
    Box {
        Row(
            Modifier
                 .padding(top = 28.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.day_clear),
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
                text = "Temperature",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp
            )
            Text(
                text = "Country",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        Row(
            Modifier
                .padding(top = 28.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(text = "WindNow", fontSize = 18.sp, color = Color.White)
            Text(text = "Humidity", fontSize = 15.sp, color = Color.White)
            Text(text = "Precipitation", fontSize = 15.sp, color = Color.White)
        }

    }

}


@Composable
fun currentDailyForecast() {
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
                .padding(top = 48.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temperature",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp
            )


        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//  screenWeather()
//}