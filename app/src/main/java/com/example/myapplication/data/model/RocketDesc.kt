package com.example.myapplication.data.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.FragmentRocketDescBinding

class RocketDescActivity : AppCompatActivity() {
    private lateinit var binding: FragmentRocketDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRocketDescBinding.inflate(layoutInflater) // Inflating description layout
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Back button visibility
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.setNavigationOnClickListener { // Back button listener
            onBackPressedDispatcher.onBackPressed()
        }

        @Suppress("DEPRECATION") // Suppressing api level warning
        val rocket = intent.getParcelableExtra<Rocket>("rocket") // Getting parcelized rocket object

        binding.textRocketName.text = rocket?.name
        binding.textFirstFlight.text = "First flight: ${rocket?.firstFlight}"
        binding.textHeight.text = "Height: ${rocket?.height?.meters}m"
        binding.textDiameter.text = "Diameter: ${rocket?.diameter?.meters}m"
    }


}