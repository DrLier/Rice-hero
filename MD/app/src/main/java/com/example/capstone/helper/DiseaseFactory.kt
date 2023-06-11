package com.example.capstone.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.di.InjectionDiseaseRepo
import com.example.capstone.mainpage.ui.pestdisease.PestDiseaseViewModel
import com.example.capstone.repository.DiseaseRepository

class DiseaseFactory private constructor(private val diseaseRepository: DiseaseRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PestDiseaseViewModel::class.java)) {
            return PestDiseaseViewModel(diseaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance : DiseaseFactory? = null
        fun getInstance(context: Context) : DiseaseFactory =
            instance ?: synchronized(this) {
                instance ?: DiseaseFactory(InjectionDiseaseRepo.provideRepository(context))
            }.also { instance = it }
    }
}