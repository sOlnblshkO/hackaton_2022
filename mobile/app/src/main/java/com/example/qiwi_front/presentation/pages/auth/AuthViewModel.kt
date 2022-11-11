package com.example.qiwi_front.presentation.pages.auth

import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModelBase() {

    fun someFun(){
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
        }
    }
}