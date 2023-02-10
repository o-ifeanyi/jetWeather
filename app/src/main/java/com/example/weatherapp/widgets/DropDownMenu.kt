package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens


@Composable
fun DropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    val items = mapOf(
        "Favourite" to Icons.Default.FavoriteBorder,
        "About" to Icons.Default.Info
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 50.dp, right = 10.dp),
    ) {
        DropdownMenu(
            expanded = showDialog.value,
            onDismissRequest = {
                showDialog.value = false
            }) {
            items.forEach { (text, imageVector) ->
                DropdownMenuItem(
                    onClick = {
                        showDialog.value = false
                        navController.navigate(
                            when (text) {
                                "Favourite" -> WeatherScreens.Favourites.name
                                else -> WeatherScreens.About.name
                            }
                        )
                    }) {
                    Icon(imageVector = imageVector, contentDescription = "icon")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = text)
                }
            }
        }
    }
}