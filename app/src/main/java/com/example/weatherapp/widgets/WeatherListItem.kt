package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapp.models.WeatherObject
import com.example.weatherapp.utils.formatDate


@Composable
fun WeatherListItem(weather: WeatherObject) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(20))
            .padding(vertical = 5.dp), color = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = formatDate(weather.dt).split(",")[0])
            WeatherImage(imageUrl, modifier = Modifier.size(30.dp))
            Surface(
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.clip(shape = RoundedCornerShape(50))
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color.White,
                )
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        this.append("${weather.main.temp_max}°")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        this.append("${weather.main.temp_min}°")
                    }
                }, style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}