package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.services.RetrofitInstance
import kotlinx.coroutines.launch
import android.util.Log
import com.example.myapplication.models.RocketAdapter
import com.example.myapplication.models.RocketDescActivity
import com.example.myapplication.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration





class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Main activity binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Main activity inflation
        setContentView(binding.root)

        binding.recyclerViewRockets.layoutManager = LinearLayoutManager(this) // Setting layout manager for recycler view object
        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL) // Putting some dividers to get cleaner view
        binding.recyclerViewRockets.addItemDecoration(divider)


        lifecycleScope.launch { // Coroutine start
            try {
                val rockets = RetrofitInstance.api.getRockets() // Async call to get rockets from API
                rockets.forEach {
                    Log.d("SpaceX", "Rocket: ${it.name}")
                }
                val adapter = RocketAdapter(rockets) { rocket -> // Triggering click event
                    val intent = Intent(this@MainActivity, RocketDescActivity::class.java) //
                    intent.putExtra("rocket", rocket) // Parcelize Rocket
                    startActivity(intent)
                }
                binding.recyclerViewRockets.adapter = adapter

            } catch (e: Exception) {
                Log.e("SpaceX", "API error: ${e.message}")
            }
        }
    }
}

