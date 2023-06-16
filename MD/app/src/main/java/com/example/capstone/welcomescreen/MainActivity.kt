package com.example.capstone.welcomescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.accesslocation.AccessLocationActivity
import com.example.capstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this@MainActivity, AccessLocationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}