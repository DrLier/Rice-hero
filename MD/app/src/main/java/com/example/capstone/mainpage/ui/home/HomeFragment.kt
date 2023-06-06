package com.example.capstone.mainpage.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.capstone.R
import com.example.capstone.data.WeatherPreference
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.helper.HomeFactory
import com.example.capstone.model.Weather
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "weather")

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

        val pref = WeatherPreference.getInstance(requireContext().dataStore)
        val factory : HomeFactory = HomeFactory.getInstance(pref)
        homeViewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        binding.buttonTakePicture.setOnClickListener {
            if (!allPermissionGranted()) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                it.findNavController().navigate(R.id.action_navigation_home_to_cameraActivity)
            }
        }

        binding.descriptionPlant.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_detailPlantFragment)
        )

        binding.pestAndDisease.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_pestDiseaseFragment)
        )

        homeViewModel.getWeather().observe(requireActivity(), {
            binding.location.text = it.location
            binding.temperature.text = it.temperature.toString()
            binding.information.text = it.description
        })

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    this.location = location
                    homeViewModel.getCurrentWeather(location)
                }
            }
        }

        homeViewModel.checkCurrentWeather().observe(requireActivity(), {
            if (it != null) {
                val celcius = it.main.temp.toInt() - 273
                Log.d("SUHU", celcius.toString())
                homeViewModel.saveWeather(
                    Weather(
                        it.name,
                        celcius,
                        it.weather[0].main
                    )
                )
            }
        })

    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionGranted()) {
                Toast.makeText(
                    requireContext(),
                    "Tidak Diberikan Permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}