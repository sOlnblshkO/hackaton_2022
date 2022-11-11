package com.example.qiwi_front.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qiwi_front.R
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .apply {
                fragments.forEach {
                    beginTransaction().remove(it).commit()
                }
                beginTransaction()
                    .replace(R.id.mainFragmentContainer, AuthFragment.newInstance())
                    .commit()
            }
    }

}