package com.example.weatherapp.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    val keyboard = LocalSoftwareKeyboardController.current
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
                    text = "Search",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            InputField {
                keyboard?.hide()
                navController.navigate(WeatherScreens.HomeScreen.name + "/$it") {
                    popUpTo(WeatherScreens.HomeScreen.name) { inclusive = true }
                }
            }
        }
    }
}

@Composable
private fun InputField(onSubmit: (String) -> Unit) {
    val input = remember {
        mutableStateOf("")
    }
    val valid = remember(input.value) {
        input.value.trim().isNotEmpty()
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        enabled = true,
        value = input.value, onValueChange = { input.value = it },
        keyboardActions = KeyboardActions(
            onSearch = {
                if (!valid) return@KeyboardActions
                onSubmit.invoke(input.value)
                input.value = ""
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        singleLine = true,
    )
}