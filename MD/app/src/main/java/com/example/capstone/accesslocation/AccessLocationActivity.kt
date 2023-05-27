package com.example.capstone.accesslocation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.SessionPreference
import com.example.capstone.databinding.ActivityAccessLocationBinding
import com.example.capstone.helper.SessionFactory
import com.example.capstone.mainpage.MainPage

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "session")

class AccessLocationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccessLocationBinding
    private lateinit var accessLocationViewModel : AccessLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val pref = SessionPreference.getInstance(dataStore)
        accessLocationViewModel = ViewModelProvider(this@AccessLocationActivity, SessionFactory(pref))[AccessLocationViewModel::class.java]

        binding.permissionButton.setOnClickListener {
            permissionLocation()
        }
    }

    private fun permissionLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            accessLocationViewModel.saveSession(true)
            val intent = Intent(this@AccessLocationActivity, MainPage::class.java)
            startActivity(intent)
        }
        else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            permissionLocation()
        }
    }
}