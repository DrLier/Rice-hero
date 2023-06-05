package com.example.capstone.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantDisease(
    val name : String,
    val type : String,
    val photo : Int,
    val symptom : String,
    val cause : String,
    val effect : String,
    val control : String
) : Parcelable
