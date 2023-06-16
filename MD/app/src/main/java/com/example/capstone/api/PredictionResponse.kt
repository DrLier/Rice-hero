package com.example.capstone.api

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

    @field:SerializedName("prediction")
    val prediction: Int? = null

)
