package com.example.capstone.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.api.PredictionResponse
import com.example.capstone.repository.PredictionRepository
import java.io.File

class CameraViewModel(private val predictionRepository: PredictionRepository) : ViewModel() {

    fun uploadImage(file : File) = predictionRepository.uploadImage(file)

    fun checkPrediction() : LiveData<PredictionResponse?> = predictionRepository.predictionResponse

    fun changePredictionToNull() = predictionRepository.changePredictionToNull()
}