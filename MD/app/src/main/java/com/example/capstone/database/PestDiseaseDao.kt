package com.example.capstone.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstone.model.PlantDisease

@Dao
interface PestDiseaseDao {
    @Query("SELECT * FROM listPestDisease")
    fun getListPestDisease() : LiveData<List<PlantDisease>>

    @Query("SELECT * FROM listPestDisease WHERE id = :id")
    fun getDisease(id : Int) : LiveData<PlantDisease>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInitiatePestDisease(plantDisease: List<PlantDisease>)

    @Query("DELETE FROM listPestDisease")
    suspend fun deleteAll()
}