package com.example.capstone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.api.ApiCamera
import com.example.capstone.api.PredictionResponse
import retrofit2.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class PredictionRepository {

    private val _predictionResult = MutableLiveData<PredictionResponse?>()
    val predictionResponse : LiveData<PredictionResponse?> = _predictionResult

    fun uploadImage(file : File) {
        Log.d("TAG", file.name)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart : MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        val client = ApiCamera.getApiServiceCamera().uploadPicture(imageMultipart)
        client.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(
                call: Call<PredictionResponse>,
                response: Response<PredictionResponse>
            ) {
                if (response.isSuccessful) {
                    _predictionResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Log.d("TAG", "ERROR")
            }
        })
    }

    fun changePredictionToNull() {
        _predictionResult.value = null
    }

    companion object {
        @Volatile
        private var INSTANCE : PredictionRepository? = null

        fun getInstance() : PredictionRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PredictionRepository()
            }.also { INSTANCE = it }
    }
}