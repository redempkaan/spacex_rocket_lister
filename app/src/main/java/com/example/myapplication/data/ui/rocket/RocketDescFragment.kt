package com.example.myapplication.data.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.ui.common.ImageLoader
import com.example.myapplication.di.MyApp
import kotlinx.coroutines.launch
import com.example.myapplication.databinding.FragmentRocketDescBinding

class RocketDescFragment : Fragment() { // Fragment class to show the description of the selected rocket

    private var _binding: FragmentRocketDescBinding? = null
    private val binding get() = _binding!!

    private val rocketRepository by lazy {
        (requireActivity().application as MyApp).appContainer.rocketRepository // Getting rocket repo with manuel DI
    }

    private val imageLoader: ImageLoader by lazy {
        (requireActivity().application as MyApp).appContainer.imageLoader // Getting image loader with manuel DI
    }

    override fun onCreateView( // Inflating and printing layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketDescBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = RocketDescFragmentArgs.fromBundle(requireArguments()) // Getting safe args
        val rocket = args.rocket

        viewLifecycleOwner.lifecycleScope.launch {

                binding.textRocketName.text = rocket.name
                binding.textFirstFlight.text = "First flight: ${rocket.firstFlight}"
                binding.textHeight.text = "Height: ${rocket.height.meters}m"
                binding.textDiameter.text = "Diameter: ${rocket.diameter.meters}"
                imageLoader.load(rocket.flickrImages[1], binding.imageRocket)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}