package com.example.myapplication.model.common

import com.bumptech.glide.Glide
import android.widget.ImageView

interface ImageLoader {
    fun load(url: String?, imageView: ImageView)
}

class GlideImageLoader : ImageLoader { // Image loader class that uses Glide library
    override fun load(url: String?, imageView: ImageView) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}