package ru.punkoff.fooddelivery.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: DeliveryApi) : NetworkRepository {
    override fun getMenu() = flow<NetworkState> {
        var size = 0
        while (size != 5) {
            val id = (0..10000).random()
            val item = api.getMenuItem(id)
            if (item.imageUrl != null && item.title != "") {
                size++
                emit(NetworkState.Success(item))
            }
        }
    }.catch {
        emit(NetworkState.Error(it))
    }
}