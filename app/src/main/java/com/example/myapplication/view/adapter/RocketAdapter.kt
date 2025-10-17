package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentRocketInfoBinding
import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.di.MyApp
import com.example.myapplication.model.common.ImageLoader

class RocketAdapter(
    private val onItemClick: (Rocket) -> Unit
) : ListAdapter<Rocket, RocketAdapter.RocketViewHolder>(RocketDiffCallback()) {

    inner class RocketViewHolder(val binding: FragmentRocketInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Getting image loader from container
        private val imageLoader: ImageLoader by lazy {
            val app = binding.root.context.applicationContext as MyApp
            app.appContainer.imageLoader
        }

        fun bind(rocket: Rocket) { // Printing rocket infos and setting a listene

            binding.textRocketName.text = rocket.name

            imageLoader.load(rocket.flickrImages.firstOrNull(), binding.imageRocket)

            binding.root.setOnClickListener {
                onItemClick(rocket)
            }
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