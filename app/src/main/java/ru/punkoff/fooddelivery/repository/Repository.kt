package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.ui.menu.MenuViewState

interface Repository {
    suspend fun getMenu(): MenuViewState
    suspend fun insertToCart(model: FoodModel)
    suspend fun getOrders(): MenuViewState
    suspend fun clearOrders(): MenuViewState
    suspend fun getCachedData(): MenuViewState
}