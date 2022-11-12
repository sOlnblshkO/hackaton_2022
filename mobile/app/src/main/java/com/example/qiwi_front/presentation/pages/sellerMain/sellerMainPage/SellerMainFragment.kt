package com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage

import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerMainBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.sellerMain.slides.profile.ProfileFragment
import com.example.qiwi_front.presentation.pages.sellerMain.slides.scanner.ScannerFragment

class SellerMainFragment : FragmentBase<FragmentSellerMainBinding, SellerMainViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.sellerBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_scanner -> {
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.sellerMainFragmentContainer, ScannerFragment.newInstance())
                        ?.commit();
                }

                R.id.nav_profile -> {
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.sellerMainFragmentContainer, ProfileFragment())?.commit();
                }
            }
            true
        }
        fragmentManager?.beginTransaction()
            ?.replace(R.id.sellerMainFragmentContainer, ScannerFragment.newInstance())
            ?.commit();
    }

    companion object {
        fun newInstance() = SellerMainFragment()
    }

    override fun getViewModelClass(): Class<SellerMainViewModel> = SellerMainViewModel::class.java

    override fun getViewBinding(): FragmentSellerMainBinding =
        FragmentSellerMainBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerMainStates


}