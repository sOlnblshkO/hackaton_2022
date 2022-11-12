package com.example.qiwi_front.presentation.pages.auth.slides.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.consts.AppSettings
import com.example.qiwi_front.utils.consts.AppSettings.Companion.IsAuth
import com.example.qiwi_front.utils.enums.StateEnum
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.qiwi_front.utils.helpers.sharedPreferences.SharedPreferencesUsage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerAuthViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModelBase() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

    var authorized = MutableLiveData(false)

    fun authorize() {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            sharedPreferencesUsage.putBoolean(context, IsAuth, true)
            sharedPreferencesUsage.putStringSharedPreferences(
                context,
                AppSettings.UserRole,
                UserRoleEnum.Customer.name
            )
            authorized.postValue(true)
            state.postValue(StateEnum.Normal)
        }
    }

}