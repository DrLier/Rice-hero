package com.example.capstone.mainpage.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.capstone.R
import com.example.capstone.api.ApiConfig
import com.example.capstone.api.WeatherResponse
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.mainpage.ui.schedule.ScheduleViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        getMyLastLocation()
    }

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    getWeather(location)
                }
            }
        }
     }

    private fun getWeather(location: Location) {
        val client = ApiConfig.getApiService().getCurrentWeather(location.latitude, location.longitude, "9e3120b9fe7fd347f870ba5d971f7b9c")
        try {
            client.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    binding.location.text = response.body()?.name
                    binding.information.text = response.body()?.weather!![0].description
                    Log.d("Location", response.body()?.name.toString())
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("TAG", "Error")
                }
            })
        }catch (E : Exception) {
            Log.d("Tag", E.toString())
        }
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
        const val RESULT_CODE = 1
    }
}