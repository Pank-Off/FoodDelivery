package ru.punkoff.fooddelivery.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.punkoff.fooddelivery.model.CachedFood
import ru.punkoff.fooddelivery.model.FoodModel

@Database(entities = [FoodModel::class, CachedFood::class], version = 1, exportSchema = false)
@TypeConverters(ListFoodTypeConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}