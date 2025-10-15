package com.example.myapplication.controller

import androidx.lifecycle.lifecycleScope
import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.view.adapter.RocketAdapter
import com.example.myapplication.model.repository.RocketRepository
import com.example.myapplication.databinding.FragmentRocketListBinding
import kotlinx.coroutines.launch
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.myapplication.view.fragment.RocketListFragmentDirections
import com.example.myapplication.model.common.ImageLoader

class RocketListController(
    private val fragment: androidx.fragment.app.Fragment,
    private val binding: FragmentRocketListBinding,
    private val repository: RocketRepository,
    private val imageLoader: ImageLoader
) {
    fun loadRockets() {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            try {
                val rockets = repository.getRockets()
                binding.recyclerViewRockets.adapter =
                    RocketAdapter(rockets, imageLoader) { rocket ->
                        Log.d("RocketClick", "Clicked rocket: ${rocket.name}")
                        navigateToDetail(rocket) // Navigating to desc fragment
                    }
            } catch (e: Exception) {
                Log.e("RocketListController", "Error loading rockets: ${e.message}")
            }
        }
    }

    private fun navigateToDetail(rocket: Rocket) {
        val action =
            RocketListFragmentDirections.actionRocketListFragmentToRocketDescFragment(rocket)
        fragment.findNavController().navigate(action)
    }
}