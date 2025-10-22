package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentRocketInfoBinding
import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.model.common.ImageLoader

class RocketAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: (Rocket) -> Unit
) : ListAdapter<Rocket, RocketAdapter.RocketViewHolder>(RocketDiffCallback()) {

    inner class RocketViewHolder(val binding: FragmentRocketInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentRocket: Rocket? = null

        init { // Setting click listener only at the creation of the view holder
            binding.root.setOnClickListener {
                currentRocket?.let(onItemClick)
            }
        }

        fun bind(rocket: Rocket) { // Printing rocket infos
            currentRocket = rocket
            binding.textRocketName.text = rocket.name

            imageLoader.load(rocket.flickrImages.firstOrNull(), binding.imageRocket)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val binding = FragmentRocketInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RocketDiffCallback : DiffUtil.ItemCallback<Rocket>() { // Diff class to check if rocket object is changed
    override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean =
        oldItem == newItem
}