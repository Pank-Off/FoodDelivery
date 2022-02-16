package ru.punkoff.fooddelivery.ui.menu.viewmodels

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.punkoff.fooddelivery.base.BaseViewModel
import ru.punkoff.fooddelivery.repository.Repository
import ru.punkoff.fooddelivery.ui.menu.MenuViewState
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repo: Repository) : BaseViewModel() {

    private val menuLiveData = MutableLiveData<MenuViewState>(MenuViewState.EMPTY)
    fun requestData() {
        cancelJob()
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            menuLiveData.postValue(repo.getMenu())
        }
    }

    fun observeLiveData() = menuLiveData
}