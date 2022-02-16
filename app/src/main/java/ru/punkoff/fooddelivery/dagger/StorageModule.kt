package ru.punkoff.fooddelivery.dagger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.punkoff.fooddelivery.room.DataBase
import ru.punkoff.fooddelivery.room.FoodDao
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, DataBase::class.java, "orders_db")
            .build()
}

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun provideFoodDao(dataBase: DataBase): FoodDao {
        return dataBase.foodDao()
    }
}
