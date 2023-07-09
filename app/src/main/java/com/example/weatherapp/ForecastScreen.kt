package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.data.DayForecast
import com.example.weatherapp.data.ForecastTemp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

val temp1 = ForecastTemp(72F, 65F, 80F) // 8/1/2023
val temp2 = ForecastTemp(76F, 66F, 81F)
val temp3 = ForecastTemp(77F, 67F, 82F)
val temp4 = ForecastTemp(78F, 68F, 83F)
val temp5 = ForecastTemp(79F, 69F, 84F)
val temp6 = ForecastTemp(80F, 70F, 85F)
val temp7 = ForecastTemp(81F, 71F, 86F)
val temp8 = ForecastTemp(82F, 72F, 87F)
val temp9 = ForecastTemp(83F, 73F, 88F)
val temp10 = ForecastTemp(84F, 74F, 89F)
val temp11 = ForecastTemp(85F, 75F, 90F)
val temp12 = ForecastTemp(86F, 76F, 91F)
val temp13 = ForecastTemp(87F, 77F, 92F)
val temp14 = ForecastTemp(88F, 78F, 93F)
val temp15 = ForecastTemp(89F, 79F, 94F)
val temp16 = ForecastTemp(90F, 80F, 95F) // 8/16


val ForecastItems = listOf(
    // time 12:00pm UTC / 7:00am local
    DayForecast(
        1690891200,
        1690887540,
        1690940400,
        temp1,
        1023F,
        100
    ), // 8/1/2023 rise: 5:59am local set: 8:40pm
    DayForecast(
        1690977600,
        1690974060,
        1691026680,
        temp2,
        1025F,
        47
    ), // 8/2 rise: 6:01am set: 8:38pm
    DayForecast(
        1691064000,
        1691060520,
        1691113020,
        temp3,
        1015F,
        55
    ), // 8/3 rise: 6:02am set: 8:37pm
    DayForecast(
        1691150400,
        1691146980,
        1691199360,
        temp4,
        1018F,
        48
    ), // 8/4 rise: 6:03am set: 8:36pm
    DayForecast(
        1691236800,
        1691233440,
        1691285640,
        temp5,
        1021F,
        51
    ), // 8/5 rise: 6:04am set: 8:34pm
    DayForecast(
        1691323200,
        1691319900,
        1691371980,
        temp6,
        1024F,
        48
    ), // 8/6 rise: 6:05am set: 8:33pm
    DayForecast(
        1691409600,
        1691406360,
        1691458320,
        temp7,
        1012F,
        46
    ), // 8/7 rise: 6:06am set: 8:32pm
    DayForecast(
        1691496000,
        1691492820,
        1691544600,
        temp8,
        1016F,
        60
    ), // 8/8 rise: 6:07am set: 8:30pm
    DayForecast(
        1691582400,
        1691579340,
        1691630940,
        temp9,
        1020F,
        57
    ), // 8/9 rise: 6:09am set: 8:29pm
    DayForecast(
        1691668800,
        1691665800,
        1691717220,
        temp10,
        1017F,
        50
    ), // 8/10 rise: 6:10am set: 8:27pm
    DayForecast(
        1691755200,
        1691752260,
        1691803560,
        temp11,
        1026F,
        41
    ), // 8/11 rise: 6:11am set: 8:26pm
    DayForecast(
        1691841600,
        1691838720,
        1691889840,
        temp12,
        1019F,
        47
    ), // 8/12 rise: 6:12am set: 8:24pm
    DayForecast(
        1691928000,
        1691925180,
        1691976180,
        temp13,
        1011F,
        53
    ), // 8/13 rise: 6:13am set: 8:23pm
    DayForecast(
        1692014400,
        1692011640,
        1692062460,
        temp14,
        1017F,
        44
    ), // 8/14 rise: 6:14am set: 8:21pm
    DayForecast(
        1692100800,
        1692098160,
        1692148800,
        temp15,
        1022F,
        69
    ), // 8/15 rise: 6:16am set: 8:20pm
    DayForecast(
        1692187200,
        1692184620,
        1692235080,
        temp16,
        1020F,
        52
    ), // 8/16 rise: 6:17am set: 8:18pm
)

@Composable
fun ForecastScreen() {
    LazyColumn {
        items(items = ForecastItems) {
            DayForecastView(daysForecast = it)
        }
    }
}

@Composable
fun DayForecastView(daysForecast: DayForecast) {
    val image: Painter = painterResource(id = R.drawable._1d)
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = image,
                contentDescription = "clear sky",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "${monthDayFormat(daysForecast.date.toString())}",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Temp: " + daysForecast.temp.day.toInt() + "°",
                    fontSize = 14.sp
                )
                Row() {
                    Text(
                        text = "High: " + daysForecast.temp.max.toInt() + "°",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Low: " + daysForecast.temp.min.toInt() + "°",
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Sunrise: " + sunRiseSetFormat(daysForecast.sunrise.toString()),
                    fontSize = 14.sp
                )
                Text(
                    text = "Sunset: " + sunRiseSetFormat(daysForecast.sunset.toString()),
                    fontSize = 14.sp
                )
            }
        }

    }
}

fun monthDayFormat(date: String): String? {
    val formatter = SimpleDateFormat("MMM d")
    val daysDate = Date(date.toLong() * 1000)
    return formatter.format(daysDate)
}

fun sunRiseSetFormat(date: String): String? {
    val formatter = SimpleDateFormat("h:mm aa")
    val daysDate = Date(date.toLong() * 1000)
    return formatter.format(daysDate)
}