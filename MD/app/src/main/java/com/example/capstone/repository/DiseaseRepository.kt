package com.example.capstone.repository

import androidx.lifecycle.LiveData
import com.example.capstone.database.PestDiseaseDao
import com.example.capstone.model.PlantDisease

class DiseaseRepository private constructor(
    private val pestDiseaseDao : PestDiseaseDao
){

    fun getListPestDisease() : LiveData<List<PlantDisease>> = pestDiseaseDao.getListPestDisease()

    fun getDisease(id : Int) : LiveData<PlantDisease> = pestDiseaseDao.getDisease(id)

    companion object {
        @Volatile
        private var instance : DiseaseRepository? = null

        fun getInstance(pestDiseaseDao: PestDiseaseDao) : DiseaseRepository =
            instance ?: synchronized(this) {
                instance ?: DiseaseRepository(pestDiseaseDao)
            }.also { instance = it }
    }
}