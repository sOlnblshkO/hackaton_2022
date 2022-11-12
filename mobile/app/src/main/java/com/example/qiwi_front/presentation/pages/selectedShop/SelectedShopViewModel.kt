package com.example.qiwi_front.presentation.pages.selectedShop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.presentation.pages.selectedShop.contracts.LoadedShopData
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedShopViewModel @Inject constructor() : ViewModelBase() {

    val loadedShopData = MutableLiveData<LoadedShopData>()

    fun loadData(selectedShopId: Int) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            loadedShopData.postValue(LoadedShopData(
                selectedShopId, "Крутое заведение","someUrl", "By koyash"
            ))
            state.postValue(StateEnum.Normal)
        }
    }
    // TODO: Implement the ViewModel
}