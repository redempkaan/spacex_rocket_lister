package com.example.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.di.MyApp
import com.example.myapplication.databinding.FragmentRocketDescBinding
import com.example.myapplication.controller.RocketDescController

class RocketDescFragment : Fragment() {

    private var _binding: FragmentRocketDescBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: RocketDescController // Controller for the desc fragment

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
        controller = RocketDescController( // Constructing the controller
            binding = binding,
            imageLoader = appContainer.imageLoader
        )

        val args = RocketDescFragmentArgs.fromBundle(requireArguments()) // Getting specified rocket from the safe args
        val rocket = args.rocket
        controller.showRocketDetails(rocket)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}