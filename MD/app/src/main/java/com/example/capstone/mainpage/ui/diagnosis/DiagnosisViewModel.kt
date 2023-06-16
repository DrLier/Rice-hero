package com.example.capstone.mainpage.ui.diagnosis

import androidx.lifecycle.ViewModel
import com.example.capstone.repository.DiseaseRepository

class DiagnosisViewModel(private val diseaseRepository: DiseaseRepository) : ViewModel() {

    fun getDisease(id : Int) = diseaseRepository.getDisease(id)
}