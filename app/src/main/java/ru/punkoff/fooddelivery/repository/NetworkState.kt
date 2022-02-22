package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel

sealed class NetworkState {
    data class Error(val throwable: Throwable) : NetworkState()
    data class Success(val foodModel: FoodModel) : NetworkState()
}
