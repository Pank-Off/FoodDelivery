package ru.punkoff.fooddelivery.model

import android.graphics.drawable.Drawable

data class PizzaModel(
    val title: String,
    val description: String,
    val price: Int = 345,
    val image: Drawable
)