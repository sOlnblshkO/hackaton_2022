package com.example.qiwi_front.presentation.pages.sellerMain.slides.profile

import android.content.Context
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.utils.consts.AppSettings
import com.example.qiwi_front.utils.helpers.sharedPreferences.SharedPreferencesUsage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModelBase() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

    fun exit() {
        sharedPreferencesUsage.removePreferenceValueSharedPreferences(
            context,
            AppSettings.IsAuth
        )
    }
}