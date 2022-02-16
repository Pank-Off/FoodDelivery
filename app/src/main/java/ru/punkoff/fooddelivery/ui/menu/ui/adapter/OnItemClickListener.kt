package ru.punkoff.fooddelivery.ui.menu.ui.adapter

import ru.punkoff.fooddelivery.model.FoodModel

interface OnItemClickListener {
    fun onClick(model: FoodModel)
}