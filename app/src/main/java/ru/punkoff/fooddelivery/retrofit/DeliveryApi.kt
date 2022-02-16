package ru.punkoff.fooddelivery.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.punkoff.fooddelivery.model.FoodModel

interface DeliveryApi {

    @GET("food/menuItems/{id}?")
    fun getMenuItem(
        @Path("id") foodId: Long,
        @Query("apiKey") apiKey: String = DELIVERY_API_KEY
    ): Deferred<FoodModel>
}

const val DELIVERY_API_KEY = "a813baf27fec478385d8994fea8cb01d"