package com.example.capstone.mainpage.ui.diagnosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.ActivityDiagnosisPestDiseaseBinding
import com.example.capstone.helper.DiseaseFactory
import com.example.capstone.mainpage.ui.pestdisease.AdapterPlantDisease
import com.example.capstone.model.PlantDisease

class DiagnosisPestDisease : AppCompatActivity() {

    private lateinit var binding : ActivityDiagnosisPestDiseaseBinding
    private lateinit var diagnosisViewModel: DiagnosisViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagnosisPestDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory : DiseaseFactory = DiseaseFactory.getInstance(applicationContext)
        diagnosisViewModel = ViewModelProvider(this@DiagnosisPestDisease, factory)[DiagnosisViewModel::class.java]

        val data = intent.getIntExtra(AdapterPlantDisease.DATA, 3)

        if (data == 0) {
            val id = 3
            diagnosisViewModel.getDisease(id).observe(this@DiagnosisPestDisease, {
                setViewData(it)
            })
        } else if (data == 1) {
            val id = 2
            diagnosisViewModel.getDisease(id).observe(this@DiagnosisPestDisease, {
                setViewData(it)
            })
        } else if (data == 2) {
            val id = 1
            diagnosisViewModel.getDisease(id).observe(this@DiagnosisPestDisease, {
                setViewData(it)
            })
        } else if (data == 4) {
            val id = 4
            diagnosisViewModel.getDisease(id).observe(this@DiagnosisPestDisease, {
                setViewData(it)
            })
        }

        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setViewData(plantDisease: PlantDisease) {
        binding.apply {
            typeDisease.text = plantDisease.type
            nameOfDisease.text = plantDisease.name
            symptomDescription.text = plantDisease.symptom
            causeDescription.text = plantDisease.cause
            descriptionPreventionAndTreatment.text = plantDisease.control
        }
    }
}