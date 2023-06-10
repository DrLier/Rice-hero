package com.example.capstone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone.model.PlantDisease
import kotlinx.coroutines.CoroutineScope

@Database(entities = [PlantDisease::class], version = 1, exportSchema = false)
abstract class PestDiseaseDatabase : RoomDatabase() {
    abstract fun pestDiseaseDao() : PestDiseaseDao

    companion object {
        @Volatile
        private var INSTANCE : PestDiseaseDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : PestDiseaseDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?:Room.databaseBuilder(
                    context.applicationContext,
                    PestDiseaseDatabase::class.java, "pestdisease_database"
                ).createFromAsset("pestdisease_database.db").build()
            }
    }
}