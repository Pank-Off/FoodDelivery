package ru.punkoff.fooddelivery.repository

import android.util.Log
import kotlinx.coroutines.flow.collect
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val networkRepository: NetworkRepository
) : Repository {

    override suspend fun getMenu(): MenuViewState {

        val data = mutableListOf<FoodModel>()
        var state: MenuViewState = MenuViewState.EMPTY
        networkRepository.getMenu().collect {
            when (it) {
                is NetworkState.Error -> {
                    it.throwable.printStackTrace()
                    state = MenuViewState.ERROR(it.throwable)
                    return@collect
                }
                is NetworkState.Success -> data.add(it.foodModel)
            }
        }
        if (state is MenuViewState.ERROR) {
            return state
        }
        Log.e(javaClass.simpleName, "DataSize: ${data.size}")
        localRepository.saveCached(data)

        return MenuViewState.Success(data)
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

    override suspend fun getCachedData(): MenuViewState {
        return localRepository.getMenuCached()
    }
}