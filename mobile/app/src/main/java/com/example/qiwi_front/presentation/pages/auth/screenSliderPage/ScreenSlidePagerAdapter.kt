package com.example.qiwi_front.presentation.pages.auth.screenSliderPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.qiwi_front.presentation.pages.auth.slides.customer.CustomerAuthFragment
import com.example.qiwi_front.presentation.pages.auth.slides.seller.SellerAuthFragment

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return SellerAuthFragment.newInstance()
        } else {
            return CustomerAuthFragment.newInstance()
        }
    }
}