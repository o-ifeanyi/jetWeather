package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.about.AboutScreen
import com.example.weatherapp.screens.favourites.FavouritesScreen
import com.example.weatherapp.screens.home.HomeScreen
import com.example.weatherapp.screens.home.HomeViewModel
import com.example.weatherapp.screens.search.SearchScreen
import com.example.weatherapp.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController, city = "abuja")
        }
        composable(
            WeatherScreens.HomeScreen.name + "/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val city = it.arguments?.getString("city", "abuja")
            viewModel.getWeather("$city")
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.Favourites.name) {
            FavouritesScreen(navController = navController)
        }
        composable(WeatherScreens.About.name) {
            AboutScreen(navController = navController)
        }
    }
}