package com.example.capstone.di

import android.content.Context
import com.example.capstone.database.PestDiseaseDatabase
import com.example.capstone.repository.DiseaseRepository


object InjectionDiseaseRepo {
    fun provideRepository(context: Context) : DiseaseRepository {
        val database = PestDiseaseDatabase.getDatabase(context)
        val dao = database.pestDiseaseDao()
        return DiseaseRepository.getInstance(dao)
    }
}