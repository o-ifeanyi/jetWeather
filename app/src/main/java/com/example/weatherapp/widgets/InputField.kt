package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun InputField(imeAction: ImeAction, onSubmit: (String) -> Unit) {
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
        label = { Text(text = "Search")},
        enabled = true,
        value = input.value, onValueChange = { input.value = it },
        keyboardActions = KeyboardActions(
            onAny = {
                if (!valid) return@KeyboardActions
                onSubmit.invoke(input.value)
                input.value = ""
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        singleLine = true,
    )
}