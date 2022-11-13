package com.example.qiwi_front.presentation.pages.paymentConfirmation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.services.bill.BillService
import com.example.domain.requests.bill.Amount
import com.example.domain.requests.bill.BillRequest
import com.example.domain.requests.bill.Customer
import com.example.domain.requests.bill.PaymentMethod
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.contracts.PaymentData
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class PaymentConfirmationViewModel @Inject constructor() : ViewModelBase() {
    val paymentData: MutableLiveData<PaymentData> = MutableLiveData()

    @Inject
    lateinit var billService: BillService

    var paymentAccepted = MutableLiveData<Boolean>()

    fun loadData(paymentInfo: PaymentData) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            paymentData.postValue(paymentInfo)
            state.postValue(StateEnum.Normal)
        }

    }

    fun acceptPayment(paymentInformation: PaymentData) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            billService.makePayment(
                BillRequest(
                    Amount(
                        value = BigDecimal(paymentInformation.amount.toDouble()).setScale(
                            2,
                            RoundingMode.FLOOR
                        ).toString(),
                        currency = "RUB"
                    ),
                    PaymentMethod(
                        paymentToken = paymentInformation.token
                    ),
                    Customer(
                        paymentInformation.customerAccount
                    ),
                    requestId = paymentInformation.requestId
                )
            )
            state.postValue(StateEnum.Normal)
        }
    }
}