package ru.punkoff.fooddelivery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.punkoff.fooddelivery.utils.uniqueId

@Entity(tableName = "table_orders")
data class FoodModel(
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Double? = 0.0,
    @SerializedName("image") val imageUrl: String? = "",
    @SerializedName("id")
    @PrimaryKey
    val id: String = uniqueId,
)