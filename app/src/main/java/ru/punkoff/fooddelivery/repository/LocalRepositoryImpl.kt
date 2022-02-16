package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.room.FoodDao
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: FoodDao) : LocalRepository {
    override suspend fun insertToCart(model: FoodModel) {
        dao.insert(model)
    }

    override suspend fun getOrders(): MenuViewState {
        val data = dao.getOrders()

        return if (data.isEmpty()) MenuViewState.EMPTY else MenuViewState.Success(data)
    }

    override suspend fun clearOrders(): MenuViewState {
        dao.dropOrders()
        return MenuViewState.EMPTY
    }

    override suspend fun getMenuCached(): MenuViewState {
        //Апи оказалось платным, 150 запросов в день можно бесплатно
        //поэтому для дальнейшей отладки использую заглушки
        return MenuViewState.Success(
            listOf(
                FoodModel(
                    "Ветчина и грибы",
                    "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                    345.0
                ),
                FoodModel(
                    "Баварские колбаски",
                    "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
                    345.0
                ),
                FoodModel(
                    "Нежный лосось",
                    "Лосось, томаты, оливки,соус песто,помидорки черри",
                    345.0
                ),
                FoodModel(
                    "Ветчина и грибы",
                    "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                    345.0
                ),
                FoodModel(
                    "Баварские колбаски",
                    "Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус",
                    345.0
                ),
                FoodModel(
                    "Нежный лосось",
                    "Лосось, томаты, оливки,соус песто,помидорки черри",
                    345.0
                ),
            )
        )
    }
}