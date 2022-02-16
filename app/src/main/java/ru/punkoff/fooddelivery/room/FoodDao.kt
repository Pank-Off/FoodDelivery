package ru.punkoff.fooddelivery.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.punkoff.fooddelivery.model.FoodModel

@Dao
interface FoodDao {
    @Insert
    suspend fun insert(model: FoodModel)

    @Query("SELECT * FROM table_orders")
    suspend fun getOrders(): List<FoodModel>

    @Query("DELETE FROM table_orders")
    suspend fun dropOrders()
}