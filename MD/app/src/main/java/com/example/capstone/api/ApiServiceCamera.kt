package com.example.capstone.api

import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiServiceCamera {
    @Multipart
    @POST("predict")
    fun uploadPicture(
        @Part file : MultipartBody.Part
    ) : Call<PredictionResponse>
}
