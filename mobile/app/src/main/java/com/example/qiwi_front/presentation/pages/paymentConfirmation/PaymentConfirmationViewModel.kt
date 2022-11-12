package com.example.qiwi_front.presentation.pages.paymentConfirmation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.contracts.PaymentData
import com.example.qiwi_front.utils.enums.StateEnum
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentConfirmationViewModel: ViewModelBase() {
    val paymentData: MutableLiveData<PaymentData> = MutableLiveData()

    fun loadData(paymentInfo: PaymentData){
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            paymentData.postValue(paymentInfo)
            state.postValue(StateEnum.Normal)
        }

    }
}