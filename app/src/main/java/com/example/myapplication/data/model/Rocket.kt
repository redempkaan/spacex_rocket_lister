package com.example.myapplication.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize // Parcelize annotation is used to make this class parcelable
data class Rocket(
    val id: String,
    val name: String,

    @SerializedName("first_flight")
    val firstFlight: String,

    val country: String,
    val company: String,

    @SerializedName("flickr_images")
    val flickrImages: List<String>,

    val height:  Height,
    val diameter: Diameter,
) : Parcelable

@Parcelize
data class Height(
    val meters: Double?,
    val feet: Double?
) : Parcelable

@Parcelize
data class Diameter(
    val meters: Double?,
    val feet: Double?
) : Parcelable