package com.example.capstone.mainpage.ui.pestdisease

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.model.PlantDisease
import com.example.capstone.repository.DiseaseRepository

class PestDiseaseViewModel(private val diseaseRepository: DiseaseRepository) : ViewModel() {

    fun getPestListDisease() : LiveData<List<PlantDisease>> = diseaseRepository.getListPestDisease()
}