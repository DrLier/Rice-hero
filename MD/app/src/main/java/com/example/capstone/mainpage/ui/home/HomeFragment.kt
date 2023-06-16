package com.example.capstone.mainpage.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.R
import com.example.capstone.api.ListItem
import com.example.capstone.camera.CameraActivity
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.helper.HomeFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var location : Location

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.show()
        val factory : HomeFactory = HomeFactory.getInstance()
        homeViewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.cardViewWeather.layoutManager = layoutManager

        binding.buttonTakePicture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                it.findNavController().navigate(R.id.action_navigation_home_to_cameraActivity)
            }
            else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        binding.descriptionPlant.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_detailPlantFragment)
        )

        binding.pestAndDisease.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_pestDiseaseFragment)
        )

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    this.location = location
                    homeViewModel.getFiveDaysWeather(location)
                }
            }
        }

        homeViewModel.checkFiveDaysWeather().observe(requireActivity(), {
            if (it == null) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                setViewData(it.list)
            }
        })

    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setViewData(listItem : List<ListItem>) {
        val adapter = WeatherAdapter(listItem)
        binding.cardViewWeather.adapter = adapter
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

}