package ru.punkoff.fooddelivery.repository

import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.retrofit.DeliveryApi
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import java.util.logging.Handler
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: DeliveryApi) : Repository {

    override suspend fun getMenu(): MenuViewState {
        val data = mutableListOf<FoodModel>()
        val state: MenuViewState
//        while (data.size != 10) {
//            val id = (0..10000).random()
//            val food = api.getMenuItem(id).await()
//
//            if (food.imageUrl != null && food.title != "") {
//                data.add(food)
//            }
//        }

        /**Раскоментировать перед отправкой!!
        // state = MenuViewState.Success(data)
         */

        /**Удалить перед отправкой!!!*/
        //Апи оказалось платным, 150 запросов в день можно бесплатно
        //поэтому для дальнейшей отладки использую заглушки
        state = MenuViewState.Success(
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


        return state
    }
}