package com.example.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentRocketListBinding
import com.example.myapplication.di.MyApp
import com.example.myapplication.controller.RocketListController
import androidx.recyclerview.widget.LinearLayoutManager
class RocketListFragment : Fragment() {

    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: RocketListController // Controller for the list fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContainer = (requireActivity().application as MyApp).appContainer
        controller = RocketListController( // Constructing the controller
            fragment = this,
            binding = binding,
            repository = appContainer.rocketRepository,
            imageLoader = appContainer.imageLoader
        )

        binding.recyclerViewRockets.layoutManager = LinearLayoutManager(requireContext()) // Setting layout manager
        controller.loadRockets()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}