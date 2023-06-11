package com.example.capstone.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") appid : String
    ) : Call<WeatherResponse>
}