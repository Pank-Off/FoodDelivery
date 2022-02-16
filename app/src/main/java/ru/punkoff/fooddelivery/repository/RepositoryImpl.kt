package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: DeliveryApi) : Repository {

    override suspend fun getMenu(): MenuViewState {
        val data = mutableListOf<FoodModel>()
        val state: MenuViewState
        while (data.size != 10) {
            val id = (0..10000).random()
            val food = api.getMenuItem(id).await()

            if (food.imageUrl != null && food.title != "") {
                data.add(food)
            }
        }
        state = MenuViewState.Success(data)
        return state
    }
}