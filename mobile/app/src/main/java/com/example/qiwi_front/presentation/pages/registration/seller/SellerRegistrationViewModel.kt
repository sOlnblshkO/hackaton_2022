package com.example.qiwi_front.presentation.pages.registration.seller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.services.registration.RegistrationService
import com.example.domain.requests.registration.SellerRegistrationRequest
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerRegistrationViewModel @Inject constructor(): ViewModelBase() {
    @Inject
    lateinit var registrationService: RegistrationService

    val registered = MutableLiveData<Boolean>()

    fun createNewSeller(
        avatarUrl: String, name: String, legalName: String, category: String, description: String,
        address: String, phone: String, password: String
    ){
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            registrationService.createSeller(
                SellerRegistrationRequest(
                    avatarUrl,
                    name,
                    legalName,
                    category,
                    description,
                    address,
                    phone,
                    password
                )
            )
            registered.postValue(true)
            state.postValue(StateEnum.Normal)
        }
    }

}