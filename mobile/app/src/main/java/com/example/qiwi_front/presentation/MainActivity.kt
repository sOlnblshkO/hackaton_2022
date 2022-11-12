package com.example.qiwi_front.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qiwi_front.R
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import com.example.qiwi_front.presentation.pages.sellerMain.SellerMainPage.SellerMainFragment
import com.example.qiwi_front.presentation.pages.sellerMain.slides.scanner.ScannerFragment
import com.example.qiwi_front.utils.consts.AppSettings
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.qiwi_front.utils.helpers.sharedPreferences.SharedPreferencesUsage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (sharedPreferencesUsage.getBoolean(applicationContext, AppSettings.IsAuth)){
            if (sharedPreferencesUsage.getStringSharedPreferences(applicationContext, AppSettings.UserRole) == UserRoleEnum.Customer.name){

            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, SellerMainFragment.newInstance())
                    .commit()
            }
        }else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, AuthFragment.newInstance())
                .commit()
        }
    }

}