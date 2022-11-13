package com.example.qiwi_front.presentation.pages.customerMain.customerMainPage

import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerMainBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.slides.profile.ProfileFragment
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.ShopsListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CustomerMainFragment @Inject constructor() :
    FragmentBase<FragmentCustomerMainBinding, CustomerMainViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.customerBottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.customerMainShopButton -> {
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.customerMainContainer, ShopsListFragment.newInstance())
                        .commit()
                }
                R.id.customerMainProfileButton -> {
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.customerMainContainer, ProfileFragment.newInstance())
                        .commit()
                }
            }
            true
        }
        binding.customerBottomMenu.selectedItemId = R.id.customerMainShopButton
        setNotificationBarColor(requireContext().getColor(R.color.orange))

    }

    companion object {
        fun newInstance() = CustomerMainFragment()
    }

    override fun getViewModelClass(): Class<CustomerMainViewModel> =
        CustomerMainViewModel::class.java

    override fun getViewBinding(): FragmentCustomerMainBinding =
        FragmentCustomerMainBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerMainState

}