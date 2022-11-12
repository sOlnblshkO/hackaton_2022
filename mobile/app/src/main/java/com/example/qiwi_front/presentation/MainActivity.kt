package com.example.qiwi_front.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qiwi_front.R
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import com.example.qiwi_front.presentation.pages.profile.ProfileFragment
import com.example.qiwi_front.presentation.pages.scanner.ScannerFragment
import com.example.qiwi_front.utils.consts.AppSettings
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.qiwi_front.utils.helpers.sharedPreferences.SharedPreferencesUsage
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var currentFragmentId = -1;
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_scanner -> {
//                    if (currentFragmentId != R.id.nav_scanner) {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.mainFragmentContainer, ScannerFragment()).commit();
//                        currentFragmentId = R.id.nav_scanner;
//                    }
//                }
//                R.id.nav_profile -> {
//                    if (currentFragmentId != R.id.nav_profile) {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.mainFragmentContainer, ProfileFragment()).commit();
//                        currentFragmentId = R.id.nav_profile;
//                    }
//                }
//                else -> false
//            }
//            true
//        }
//        supportFragmentManager
//            .apply {
//                fragments.forEach {
//                    beginTransaction().remove(it).commit()
//                }
//                beginTransaction()
//                    .replace(R.id.mainFragmentContainer, AuthFragment.newInstance())
//                    .commit()
//            }

        if (sharedPreferencesUsage.getBoolean(applicationContext, AppSettings.IsAuth)){
            if (sharedPreferencesUsage.getStringSharedPreferences(applicationContext, AppSettings.UserRole) == UserRoleEnum.Customer.name){

            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, ScannerFragment.newInstance())
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