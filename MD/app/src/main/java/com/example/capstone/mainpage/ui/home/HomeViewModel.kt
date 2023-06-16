package com.example.capstone.mainpage.ui.home

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.capstone.repository.WeatherRepository

class HomeViewModel(private val weatherRepository : WeatherRepository) : ViewModel() {

    fun getFiveDaysWeather(location: Location) = weatherRepository.getFiveDaysWeather(location)

    fun checkFiveDaysWeather() = weatherRepository.fiveDaysWeather

}