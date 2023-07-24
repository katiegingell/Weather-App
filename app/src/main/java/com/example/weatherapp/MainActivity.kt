package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "CurrentConditions") {
                        composable("CurrentConditions") {
                            actionBar?.title = stringResource(id = R.string.app_name)
                            WeatherDataView()
                            ForecastButton(navController = navController)
                        }
                        composable("ForecastScreen") {
                            actionBar?.title = "Forecast"
                            ForecastScreen()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun WeatherDataView(viewModel: CurrentConditionsViewModel = hiltViewModel()) {
    val currentConData = viewModel.currentConditions.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        // city name
        Text(
            text = currentConData.value?.cityName ?: "City",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Column {
                // current temp
                Text(
                    text = currentConData.value?.currentWeather?.temp?.roundToInt()
                        .toString() + "°",
                    fontSize = 70.sp
                )
                // feels like
                Text(
                    text = "Feels like " + currentConData.value?.currentWeather?.feelsLike?.roundToInt() + "°",
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 30.dp))
            // icon
            currentConData.value?.iconUrl?.let { WeatherConditionIconCurrent(url = it) }

        }
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        // low
        Text(
            text = "Low " + currentConData.value?.currentWeather?.tempMin?.roundToInt() + "°",
            fontSize = 20.sp
        )
        // high
        Text(
            text = "High " + currentConData.value?.currentWeather?.tempMax?.roundToInt() + "°",
            fontSize = 20.sp
        )
        // humidity
        Text(
            text = "Humidity " + currentConData.value?.currentWeather?.humidity + "%",
            fontSize = 20.sp
        )
        // pressure
        Text(
            text = "Pressure " + currentConData.value?.currentWeather?.pressure?.roundToInt() + " hPa",
            fontSize = 20.sp
        )
    }
}

@Composable
fun ForecastButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate("ForecastScreen") },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            modifier = Modifier.width(200.dp)
        ) {
            Text(
                text = "Forecast",
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }

}

@Composable
fun WeatherConditionIconCurrent(
    url: String
) {
    AsyncImage(model = url, contentDescription = "", modifier = Modifier.size(120.dp))
}

@Composable
fun WeatherConditionIconForecast(
    url: String
) {
    AsyncImage(model = url, contentDescription = "", modifier = Modifier.size(60.dp))
}