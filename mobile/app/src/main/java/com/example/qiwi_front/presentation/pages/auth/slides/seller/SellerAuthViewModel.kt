package com.example.qiwi_front.presentation.pages.auth.slides.seller

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.services.auth.AuthService
import com.example.domain.requests.auth.AuthRequest
import com.example.domain.responses.auth.AuthResponse
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.shared.consts.AppSettings
import com.example.qiwi_front.utils.enums.StateEnum
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerAuthViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModelBase() {
    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage
    @Inject
    lateinit var authService: AuthService

    var authorized = MutableLiveData<AuthResponse>()

    fun authorize(phone: String, pass: String) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            authorized.postValue(
                authService.authorize(
                    AuthRequest(
                    phone, pass
                )
                )
            )
            state.postValue(StateEnum.Normal)
        }
    }
}