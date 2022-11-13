package com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage

import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerMainBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.ShopsListFragment
import com.example.qiwi_front.presentation.pages.sellerMain.slides.profile.ProfileFragment
import com.example.qiwi_front.presentation.pages.sellerMain.slides.scanner.ScannerFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerMainFragment @Inject constructor() :
    FragmentBase<FragmentSellerMainBinding, SellerMainViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.sellerBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_scanner -> {
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.sellerMainFragmentContainer, ScannerFragment.newInstance())
                        .commit()
                }

                R.id.nav_profile -> {
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.sellerMainFragmentContainer, ProfileFragment.newInstance())
                        .commit()
                }
            }
            true
        }
        binding.sellerBottomNavigation.selectedItemId = R.id.nav_scanner
        setNotificationBarColor(requireContext().getColor(R.color.orange))
    }

    companion object {
        fun newInstance() = SellerMainFragment()
    }

    override fun getViewModelClass(): Class<SellerMainViewModel> = SellerMainViewModel::class.java

    override fun getViewBinding(): FragmentSellerMainBinding =
        FragmentSellerMainBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerMainStates


}