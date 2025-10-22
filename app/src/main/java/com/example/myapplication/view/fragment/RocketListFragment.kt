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
import com.example.myapplication.model.common.ImageLoader


class RocketListFragment : Fragment() {

    private lateinit var controller: RocketListController
    private lateinit var adapter: RocketAdapter
    private lateinit var imageLoader: ImageLoader
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
        controller = RocketListController(appContainer.rocketRepository)
        imageLoader = appContainer.imageLoader


        adapter = RocketAdapter(imageLoader) { rocket ->
            navigateToDetail(rocket)
        }

        binding.recyclerViewRockets.apply {
            adapter = this@RocketListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        observeRockets()

        controller.loadRockets()
    }

    private fun observeRockets() { // Collect the UI state
        viewLifecycleOwner.lifecycleScope.launch {
            controller.uiState.collect { state ->
                when (state) {
                    is NetworkResult.Loading -> showLoading()
                    is NetworkResult.Success -> showRockets(state.data)
                    is NetworkResult.Error -> showError(state.message)
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showRockets(rockets: List<Rocket>) {
        adapter.submitList(rockets)
        binding.progressBar.visibility = View.GONE
    }

    private fun showError(errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Network Error")
            .setMessage("$errorMessage\nWould you like to retry?")
            .setCancelable(false)
            .setPositiveButton("Retry") { dialog, _ ->
                dialog.dismiss()
                controller.retry() // Retry the API call
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