package com.example.qiwi_front.presentation.pages.sellerMain.slides.profile

import android.content.Context
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.shared.consts.AppSettings
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModelBase() {

    @Inject
    lateinit var sharedPreferencesUsage: com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage

    fun exit() {
        sharedPreferencesUsage.removePreferenceValueSharedPreferences(
            context,
            com.example.shared.consts.AppSettings.IsAuth
        )
    }
}