package ru.punkoff.fooddelivery.model

import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double = 0.0,
    @SerializedName("image") val imageUrl: String? = ""
)