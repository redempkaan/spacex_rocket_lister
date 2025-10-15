package com.example.myapplication.controller

import com.example.myapplication.databinding.FragmentRocketDescBinding
import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.model.common.ImageLoader

class RocketDescController(
    private val binding: FragmentRocketDescBinding,
    private val imageLoader: ImageLoader
) {
    fun showRocketDetails(rocket: Rocket) {
        binding.textRocketName.text = rocket.name
        binding.textFirstFlight.text = "First flight: ${rocket.firstFlight}"
        binding.textHeight.text = "Height: ${rocket.height.meters}m"
        binding.textDiameter.text = "Diameter: ${rocket.diameter.meters}"
        imageLoader.load(rocket.flickrImages[1], binding.imageRocket)
    }
}