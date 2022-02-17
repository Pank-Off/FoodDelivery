package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.CachedFood
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.room.FoodDao
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: FoodDao) : LocalRepository {
    override suspend fun insertToCart(model: FoodModel) {
        dao.insert(model)
    }

    override suspend fun getOrders(): MenuViewState {
        val data = dao.getOrders()

        return if (data.isEmpty()) MenuViewState.EMPTY else MenuViewState.Success(data)
    }

    override suspend fun clearOrders(): MenuViewState {
        dao.dropOrders()
        return MenuViewState.EMPTY
    }

    override suspend fun getMenuCached(): MenuViewState {
        val data = dao.getCached()
        return MenuViewState.Success(data[0].data)
    }

    override suspend fun saveCached(items: List<FoodModel>) {
        dao.dropCached()
        dao.saveCached(CachedFood(items))
    }
}