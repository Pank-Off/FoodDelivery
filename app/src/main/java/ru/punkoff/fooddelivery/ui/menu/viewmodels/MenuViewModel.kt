package ru.punkoff.fooddelivery.ui.menu.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.punkoff.fooddelivery.base.BaseViewModel
import ru.punkoff.fooddelivery.model.FoodModel
import ru.punkoff.fooddelivery.repository.Repository
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repo: Repository
) : BaseViewModel() {

    private val _menuStateFlow = MutableStateFlow<MenuViewState>(MenuViewState.Loading)
    val menuStateFlow = _menuStateFlow.asStateFlow()

    fun requestData() {
        cancelJob()
        _menuStateFlow.value = MenuViewState.Loading
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _menuStateFlow.value = repo.getMenu()
        }
    }

    fun insertToCart(model: FoodModel) {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            repo.insertToCart(model)
        }
    }

    fun getCachedData() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            delay(3000)
            _menuStateFlow.value = repo.getCachedData()
        }
    }
}