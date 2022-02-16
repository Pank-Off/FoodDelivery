package ru.punkoff.fooddelivery.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.punkoff.fooddelivery.model.FoodModel

@Database(entities = [FoodModel::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}