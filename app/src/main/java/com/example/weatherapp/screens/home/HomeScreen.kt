package com.example.weatherapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.models.Favourite
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.favourites.FavesViewModel
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDateTime
import com.example.weatherapp.widgets.DropDownMenu
import com.example.weatherapp.widgets.WeatherImage
import com.example.weatherapp.widgets.WeatherInfoItem
import com.example.weatherapp.widgets.WeatherListItem

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val loading = viewModel.weatherState.value
    val weatherData = viewModel.weatherData.value


    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        weatherData.data?.let { MainScreen(navController = navController, weatherModel = it) }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    weatherModel: WeatherModel,
    favesViewModel: FavesViewModel = hiltViewModel()
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val weather = weatherModel.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    val allFavourites = favesViewModel.allFavourites.collectAsState().value

    val favourites = allFavourites.filter {
        it.city == weatherModel.city.name && it.country == weatherModel.city.country
    }

    Scaffold(topBar = {
        DropDownMenu(showDialog = showDialog, navController = navController)

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    if (favourites.isNotEmpty())
                        favesViewModel.deleteFavourite(favourites.first())
                    else
                        favesViewModel.addFavourite(
                            Favourite(
                                city = weatherModel.city.name,
                                country = weatherModel.city.country
                            )
                        )
                }) {
                    Icon(
                        imageVector = if (favourites.isNotEmpty()) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favourite Icon Button"
                    )
                }
            },
            title = {
                Text(
                    text = "${weatherModel.city.name}, ${weatherModel.city.country}",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    modifier = Modifier.clickable { navController.navigate(WeatherScreens.SearchScreen.name) })
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more icon",
                    modifier = Modifier.clickable { showDialog.value = true })
            },
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = formatDate(weather.dt))
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                modifier = Modifier.size(150.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.primary
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherImage(imageUrl)
                    Text(
                        text = "${weather.main.temp}°",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(text = weather.weather[0].main, fontStyle = FontStyle.Italic)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoItem(drawable = R.drawable.humidity, text = "${weather.main.humidity} %")
                WeatherInfoItem(
                    drawable = R.drawable.pressure,
                    text = "${weather.main.pressure} psi"
                )
                WeatherInfoItem(drawable = R.drawable.wind, text = "${weather.wind.speed} mph")
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoItem(
                    drawable = R.drawable.sunrise,
                    text = formatDateTime(weatherModel.city.sunrise)
                )
                WeatherInfoItem(
                    drawable = R.drawable.sunset,
                    text = formatDateTime(weatherModel.city.sunset)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "This Week",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                items(weatherModel.list) { weather ->
                    WeatherListItem(weather)
                }
            }
        }
    }
}



