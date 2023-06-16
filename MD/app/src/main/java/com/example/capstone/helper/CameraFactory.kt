package com.example.capstone.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.camera.CameraViewModel
import com.example.capstone.di.InjectionPredictionRepo
import com.example.capstone.repository.PredictionRepository

class CameraFactory private constructor(private val predictionRepository: PredictionRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CameraViewModel::class.java)) {
            return CameraViewModel(predictionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        private var INSTANCE : CameraFactory? = null
        fun getInstance() : CameraFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: CameraFactory(InjectionPredictionRepo.provideRepository())
            }.also { INSTANCE = it }
    }
}