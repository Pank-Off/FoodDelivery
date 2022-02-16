package ru.punkoff.fooddelivery.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Int = 345,
    @SerializedName("image") val imageUrl: String = ""
)