package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.WeatherAppTheme


class MainActivity : ComponentActivity() {
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun WeatherDataView() {
    val image: Painter = painterResource(id = R.drawable._1d)
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.city),
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Column() {
                Text(
                    text = stringResource(id = R.string.temperature),
                    fontSize = 70.sp
                )
                Text(
                    text = stringResource(id = R.string.feels_like_temp),
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 30.dp))
            Image(
                painter = image,
                contentDescription = "clear sky",
                modifier = Modifier.size(100.dp)
            )

        }
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(
            text = stringResource(id = R.string.low_temp),
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.high_temp),
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.humidity),
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.pressure),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}