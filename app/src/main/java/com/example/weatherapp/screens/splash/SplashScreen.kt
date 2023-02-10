package com.example.weatherapp.screens.splash


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, city: String) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )

        delay(2000L)
        navController.navigate(route = WeatherScreens.HomeScreen.name + "/$city") {
            popUpTo(WeatherScreens.SplashScreen.name) {inclusive = true}
        }
    })

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier
                    .size(250.dp)
                    .padding(20.dp)
                    .scale(scale.value),
                shape = CircleShape,
                color = Color.White,
                border = BorderStroke(
                    width = 2.dp, color = Color.LightGray
                )
            ) {
                Column(
                    modifier = Modifier.padding(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "sunny icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "Find the Sun?",
                        style = MaterialTheme.typography.h6,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}