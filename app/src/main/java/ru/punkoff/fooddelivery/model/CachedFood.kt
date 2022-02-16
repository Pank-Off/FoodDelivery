package ru.punkoff.fooddelivery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_cached_food")
data class CachedFood(
    val data: List<FoodModel>,
    @PrimaryKey val id: Int = 0,
)
