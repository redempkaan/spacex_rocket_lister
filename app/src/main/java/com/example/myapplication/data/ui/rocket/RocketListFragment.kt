package com.example.myapplication.data.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.di.MyApp
import com.example.myapplication.data.model.RocketAdapter
import com.example.myapplication.databinding.FragmentRocketListBinding
import kotlinx.coroutines.launch
import android.util.Log
import androidx.navigation.fragment.findNavController

class RocketListFragment : Fragment() {

    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    private val rocketRepository by lazy { // Getting rocket repo with manuel DI
        (requireActivity().application as MyApp).appContainer.rocketRepository
    }

    override fun onCreateView( // Inflating and printing the fragment layout
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewRockets.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            try {
                val rockets = rocketRepository.getRockets() // API Call
                binding.recyclerViewRockets.adapter = RocketAdapter(rockets, (requireActivity().application as MyApp).appContainer.imageLoader) { rocket ->
                    Log.d("RocketClick", "Clicked rocket: ${rocket.name}")

                    val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDescFragment(rocket)
                    findNavController().navigate(action)
                }
            } catch (e: Exception) {
                Log.e("SpaceX", "API error: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}