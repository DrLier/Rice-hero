package com.example.capstone.mainpage.ui.pestdisease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.FragmentPestDiseaseBinding
import com.example.capstone.helper.DiseaseFactory
import com.example.capstone.model.PlantDisease

class PestDiseaseFragment : Fragment() {

    private var _binding: FragmentPestDiseaseBinding? = null

    private val binding get() = _binding!!
    private lateinit var pestDiseaseViewModel: PestDiseaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPestDiseaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory : DiseaseFactory = DiseaseFactory.getInstance(requireContext())
        pestDiseaseViewModel = ViewModelProvider(requireActivity(), factory)[PestDiseaseViewModel::class.java]

        pestDiseaseViewModel.getPestListDisease().observe(requireActivity(), {
            showRecyclerList(it)
        })

        binding.backButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_pestDiseaseFragment_to_navigation_home)
        )
    }

    private fun showRecyclerList(plantDisease : List<PlantDisease>) {
        binding.listPlantDisease.layoutManager = GridLayoutManager(requireContext(), 2)
        val listPlantDisease = AdapterPlantDisease(plantDisease)
        binding.listPlantDisease.adapter = listPlantDisease
    }
}