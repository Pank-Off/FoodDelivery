package ru.punkoff.fooddelivery.ui.cart

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.punkoff.fooddelivery.base.BaseViewModel
import ru.punkoff.fooddelivery.repository.Repository
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: Repository
) : BaseViewModel() {

    private val cartLiveData = MutableLiveData<MenuViewState>(MenuViewState.EMPTY)
    fun requestData() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            cartLiveData.postValue(repo.getOrders())
        }
    }

    fun clearOrders() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            cartLiveData.postValue(repo.clearOrders())
        }
    }

    fun observeLiveData() = cartLiveData
}