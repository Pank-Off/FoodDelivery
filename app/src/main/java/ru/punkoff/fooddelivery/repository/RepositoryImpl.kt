package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: DeliveryApi) : Repository {

    override suspend fun getMenu(): MenuViewState {
        val state: MenuViewState
        val food = api.getMenuItem(424571).await()
        state = MenuViewState.Success(listOf(food))
        return state
    }
}