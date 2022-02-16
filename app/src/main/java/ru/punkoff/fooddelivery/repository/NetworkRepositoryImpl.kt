package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: DeliveryApi) : NetworkRepository {
    override suspend fun getMenu(): MenuViewState {
        val data = mutableListOf<FoodModel>()
        while (data.size != 10) {
            val id = (0..10000).random()
            val food = api.getMenuItem(id).await()

            if (food.imageUrl != null && food.title != "") {
                data.add(food)
            }
        }
        return MenuViewState.Success(data)
    }
}