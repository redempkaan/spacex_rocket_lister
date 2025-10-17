package com.example.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.di.MyApp
import com.example.myapplication.databinding.FragmentRocketDescBinding
import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.model.common.ImageLoader

class RocketDescFragment : Fragment() {

    private var _binding: FragmentRocketDescBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketDescBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContainer = (requireActivity().application as MyApp).appContainer

        val args = RocketDescFragmentArgs.fromBundle(requireArguments()) // Getting specified rocket from the safe args
        val rocket = args.rocket
        showRocketDetails(rocket, appContainer.imageLoader)
    }

    fun showRocketDetails(rocket: Rocket, imageLoader: ImageLoader) { // Printing rocket details
        binding.textRocketName.text = rocket.name
        binding.textFirstFlight.text = "First flight: ${rocket.firstFlight}"
        binding.textHeight.text = "Height: ${rocket.height.meters}m"
        binding.textDiameter.text = "Diameter: ${rocket.diameter.meters}"
        imageLoader.load(rocket.flickrImages[1], binding.imageRocket)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}