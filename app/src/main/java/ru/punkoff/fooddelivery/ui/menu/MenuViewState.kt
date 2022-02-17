package ru.punkoff.fooddelivery.ui.menu

import ru.punkoff.fooddelivery.model.FoodModel

sealed class MenuViewState {
    object EMPTY : MenuViewState()
    object Loading : MenuViewState()
    data class Success(val items: List<FoodModel>) : MenuViewState()
    data class ERROR(val exc:Throwable):MenuViewState()
}