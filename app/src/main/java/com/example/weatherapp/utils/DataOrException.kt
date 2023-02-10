package com.example.weatherapp.utils

class DataOrException<T, E : Exception>(var data: T? = null, var exc: E? = null)