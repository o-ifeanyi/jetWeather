package com.example.weatherapp.screens.favourites


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.widgets.FavouritesListItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(navController: NavController, favesViewModel: FavesViewModel = hiltViewModel()) {
    val favourites = favesViewModel.allFavourites.collectAsState().value
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            },
            title = {
                Text(
                    text = "Favourites",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }) {
        LazyColumn(modifier = Modifier.padding(15.dp)) {
            items(favourites) {
                FavouritesListItem(favourite = it) {
                    favesViewModel.deleteFavourite(it)
                }
            }
        }
    }
}