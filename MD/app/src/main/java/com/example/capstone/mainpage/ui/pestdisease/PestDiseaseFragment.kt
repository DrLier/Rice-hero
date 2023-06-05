package com.example.capstone.mainpage.ui.pestdisease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.R
import com.example.capstone.data.ListPlantDisease.listDataPlantDisease
import com.example.capstone.databinding.FragmentPestDiseaseBinding

class PestDiseaseFragment : Fragment() {

    private var _binding: FragmentPestDiseaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        showRecyclerList()

        binding.backButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_pestDiseaseFragment_to_navigation_home)
        )
    }

    private fun showRecyclerList() {
        binding.listPlantDisease.layoutManager = GridLayoutManager(requireContext(), 2)
        val listPlantDisease = AdapterPlantDisease(listDataPlantDisease)
        binding.listPlantDisease.adapter = listPlantDisease
    }
}