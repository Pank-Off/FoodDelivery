package ru.punkoff.fooddelivery.repository

import retrofit2.HttpException
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: DeliveryApi) : NetworkRepository {
    override suspend fun getMenu(): MenuViewState {
        return try {
            val data = mutableListOf<FoodModel>()
            while (data.size != 1) {
                val id = (0..10000).random()
                val food = api.getMenuItem(id).await()

                if (food.imageUrl != null && food.title != "") {
                    data.add(food)
                }
            }
            MenuViewState.Success(data)
        } catch (exc: HttpException) {
            exc.printStackTrace()
            MenuViewState.ERROR(exc)
        } catch (exc:UnknownHostException){
            exc.printStackTrace()
            MenuViewState.ERROR(exc)
        }
    }
}