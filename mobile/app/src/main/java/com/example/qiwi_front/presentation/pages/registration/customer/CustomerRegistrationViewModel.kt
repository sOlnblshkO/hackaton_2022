package com.example.qiwi_front.presentation.pages.registration.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.services.registration.RegistrationService
import com.example.domain.requests.registration.RegistrationRequest
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerRegistrationViewModel @Inject constructor(): ViewModelBase() {

    @Inject
    lateinit var registrationService: RegistrationService

    val registered = MutableLiveData<Boolean>()

    fun register(
        phone: String,
        name: String,
        pass: String,
        surname: String
    ) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            registrationService.createCustomer(
                RegistrationRequest(
                    phone, name, surname, pass
                )
            )
            state.postValue(StateEnum.Normal)
            registered.postValue(true)
        }
    }

}