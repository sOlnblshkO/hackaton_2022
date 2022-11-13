package com.example.qiwi_front.presentation.pages.auth.slides.seller

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    lateinit var sharedPreferencesUsage: com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage

    var authorized = MutableLiveData(false)

    fun authorize() {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            sharedPreferencesUsage.putBoolean(context, com.example.shared.consts.AppSettings.IsAuth, true)
            sharedPreferencesUsage.putStringSharedPreferences(
                context,
                com.example.shared.consts.AppSettings.UserRole,
                UserRoleEnum.Seller.name
            )
            authorized.postValue(true)
            state.postValue(StateEnum.Normal)
        }
    }
}