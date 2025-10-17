package com.example.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.controller.RocketListController
import com.example.myapplication.databinding.FragmentRocketListBinding
import com.example.myapplication.di.MyApp
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.view.adapter.RocketAdapter
import com.example.myapplication.model.rocket.Rocket
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog


class RocketListFragment : Fragment() {

    private lateinit var controller: RocketListController
    private lateinit var adapter: RocketAdapter
    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContainer = (requireActivity().application as MyApp).appContainer
        controller = RocketListController(appContainer.rocketRepository) // Controller init

        adapter = RocketAdapter { rocket ->
            navigateToDetail(rocket)
        }

        binding.recyclerViewRockets.adapter = adapter
        binding.recyclerViewRockets.layoutManager = LinearLayoutManager(requireContext())

        observeRockets()
    }

    // Trying to get rockets from API, managing UI states according to the result
    private fun observeRockets() {
        viewLifecycleOwner.lifecycleScope.launch {
            showLoading()

            when (val result = controller.loadRockets()) {
                is NetworkResult.Success -> { // Success
                    hideLoading()
                    binding.textError.visibility = View.GONE
                    adapter.submitList(result.data)
                }

                is NetworkResult.Error -> { // Error
                    hideLoading()
                    showRetryDialog(result.message ?: "Unknown error") // Show popup
                }

                is NetworkResult.Loading -> showLoading() // Loading
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showRetryDialog(errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Network Error")
            .setMessage("$errorMessage\nWould you like to retry?")
            .setCancelable(false)
            .setPositiveButton("Retry") { dialog, _ ->
                dialog.dismiss()
                observeRockets() // Retry the API call
            }
            .setNegativeButton("Exit App") { dialog, _ ->
                dialog.dismiss()
                requireActivity().finishAffinity() // Close the app completely
            }
            .show()
    }

    private fun navigateToDetail(rocket: Rocket) { // Navigate to detail fragment
        val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDescFragment(rocket)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}