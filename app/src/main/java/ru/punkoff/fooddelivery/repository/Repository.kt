package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.ui.menu.MenuViewState

interface Repository {
    suspend fun getMenu(): MenuViewState
}