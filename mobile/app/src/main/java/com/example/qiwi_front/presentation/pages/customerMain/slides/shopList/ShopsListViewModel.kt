package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.contracts.ShopListItem
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopsListViewModel @Inject constructor() : ViewModelBase() {
    fun load() {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            state.postValue(StateEnum.Normal)
        }
    }

    val shopItems = listOf(
        ShopListItem(
            1,
            "Первый",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
        ),
        ShopListItem(1, "Второй", ""),
        ShopListItem(1, "Третий", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
        ShopListItem(1, "Четвертый", ""),
    );
}