package ru.punkoff.fooddelivery.ui.cart

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.punkoff.fooddelivery.base.BaseViewModel
import ru.punkoff.fooddelivery.repository.Repository
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: Repository
) : BaseViewModel() {

    private val _cartStateFlow = MutableStateFlow<MenuViewState>(MenuViewState.Loading)
    val cartStateFlow = _cartStateFlow.asStateFlow()
    fun requestData() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _cartStateFlow.value = repo.getOrders()
        }
    }

    fun clearOrders() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _cartStateFlow.value = repo.clearOrders()
        }
    }
}