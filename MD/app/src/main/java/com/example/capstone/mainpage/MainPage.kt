package com.example.capstone.mainpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstone.R
import com.example.capstone.data.SessionPreference
import com.example.capstone.databinding.ActivityMainPageBinding
import com.example.capstone.helper.SessionFactory
import com.example.capstone.welcomescreen.MainActivity

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "session")

class MainPage : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    private lateinit var mainPageViewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = SessionPreference.getInstance(dataStore)
        mainPageViewModel = ViewModelProvider(this@MainPage, SessionFactory(pref))[MainPageViewModel::class.java]

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_page)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_schedule, R.id.navigation_feedback
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mainPageViewModel.getSession().observe(this@MainPage, {
            if (it == true) {
                Toast.makeText(this@MainPage, "Sudah pernah menjalankan aplikasi", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this@MainPage, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}