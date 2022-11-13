package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.services.sellers.SellersService
import com.example.domain.responses.sellersList.GetSellersResponse
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.contracts.ShopListItem
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopsListViewModel @Inject constructor() : ViewModelBase() {

    @Inject
    lateinit var sellersService: SellersService
    val sellers = MutableLiveData<List<GetSellersResponse>>()

    fun getList(text: String) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            sellers.postValue(sellersService.getSellers(text))
            state.postValue(StateEnum.Normal)
        }
    }
}