package ru.punkoff.fooddelivery.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.punkoff.fooddelivery.model.FoodModel

class ListFoodTypeConverter {
    @TypeConverter
    fun foodsToJson(foods: List<FoodModel>): String = Gson().toJson(foods)

    @TypeConverter
    fun jsonToFoods(foods: String) = Gson().fromJson(foods, Array<FoodModel>::class.java).toList()
}