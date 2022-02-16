package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.ui.menu.MenuViewState

interface NetworkRepository {
    suspend fun getMenu(): MenuViewState
}