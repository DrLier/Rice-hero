package com.example.capstone.mainpage.ui.detailpestdisease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.capstone.databinding.ActivityDetailPestDiseaseBinding
import com.example.capstone.mainpage.ui.pestdisease.AdapterPlantDisease
import com.example.capstone.model.PlantDisease

class DetailPestDisease : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPestDiseaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPestDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val data = intent.getParcelableExtra<PlantDisease>(AdapterPlantDisease.DATA)

        if (data != null) {
            binding.apply {
                name.text = data.name
                type.text = data.type
                Glide.with(this@DetailPestDisease).load(data.photo).into(image)
                symptomDescription.text = data.symptom
                causeDescription.text = data.cause
                effectDescription.text = data.effect
                descriptionPreventionAndTreatment.text = data.control
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}