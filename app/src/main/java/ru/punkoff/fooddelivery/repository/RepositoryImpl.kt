package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val networkRepository: NetworkRepository
) : Repository {

    private val isOnline = false
    override suspend fun getMenu(): MenuViewState {
        return if (isOnline) {
            networkRepository.getMenu()
        } else {
            localRepository.getMenuCached()
        }
    }

    override suspend fun insertToCart(model: FoodModel) {
        localRepository.insertToCart(model)
    }

    override suspend fun getOrders(): MenuViewState {
        return localRepository.getOrders()
    }

    override suspend fun clearOrders(): MenuViewState {
        return localRepository.clearOrders()
    }
}