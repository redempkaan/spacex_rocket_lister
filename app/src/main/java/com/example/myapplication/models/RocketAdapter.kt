package com.example.myapplication.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RocketInfoBinding


class RocketAdapter(private val rockets: List<Rocket>, private val onItemClick: (Rocket) -> Unit) : //Lambda to trigger click event
    RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {


    inner class RocketViewHolder(val binding: RocketInfoBinding) : // Class for recyclerview elements
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder { // Is called when a new view holder is needed
        val binding = RocketInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rockets[position]
        holder.binding.textRocketName.text = rocket.name

        Glide.with(holder.itemView.context) // Loading rocket images
            .load(rocket.flickrImages.firstOrNull())
            .into(holder.binding.imageRocket)

        holder.binding.root.setOnClickListener { // Setting up a click listener
            onItemClick(rocket)
        }
    }
    override fun getItemCount() = rockets.size // Enables recyclerview to know how many items it needs
}
