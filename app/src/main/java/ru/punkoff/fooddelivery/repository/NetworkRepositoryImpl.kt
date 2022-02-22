package ru.punkoff.fooddelivery.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: DeliveryApi) : NetworkRepository {
    override suspend fun getMenu(): Result<MenuViewState> {
        return try {
            val data = mutableListOf<FoodModel>()

            while (data.size != 5) {
                val id = (0..10000).random()
                flow {
                    emit(api.getMenuItem(id))
                }.collect {
                    if (it.imageUrl != null && it.title != "") {
                        data.add(it)
                    }
                }
            }
            Result.success(MenuViewState.Success(data))
        } catch (exc: HttpException) {
            exc.printStackTrace()
            Result.failure(exc)
        } catch (exc: UnknownHostException) {
            exc.printStackTrace()
            Result.failure(exc)
        }
    }
}