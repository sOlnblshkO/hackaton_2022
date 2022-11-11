package com.example.qiwi_front.presentation.pages.auth

import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.enums.StateEnum
import kotlinx.coroutines.launch

class AuthViewModel : ViewModelBase() {

    fun someFun(){
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
        }
    }
}