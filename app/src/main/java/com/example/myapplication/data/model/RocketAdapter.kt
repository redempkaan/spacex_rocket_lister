package com.example.myapplication.data.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.ui.common.ImageLoader
import com.example.myapplication.databinding.FragmentRocketInfoBinding


class RocketAdapter(
    private val rockets: List<Rocket>,
    private val imageLoader: ImageLoader,
    private val onItemClick: (Rocket) -> Unit
) : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {


    inner class RocketViewHolder(val binding: FragmentRocketInfoBinding) : // Class for recyclerview elements
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder { // Is called when a new view holder is needed
        val binding = FragmentRocketInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]
        holder.binding.textRocketName.text = rocket.name

        imageLoader.load(rocket.flickrImages.firstOrNull(), holder.binding.imageRocket) // Using image loader in the app container to load images

        holder.binding.root.setOnClickListener { // Setting up a click listener
            onItemClick(rocket)
        }
    }
    override fun getItemCount() = rockets.size // Enables recyclerview to know how many items it needs
}
