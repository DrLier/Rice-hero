package com.example.capstone.mainpage.ui.detailpestdisease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.R
import com.example.capstone.databinding.ActivityDetailPestDiseaseBinding

class DetailPestDisease : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPestDiseaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPestDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}