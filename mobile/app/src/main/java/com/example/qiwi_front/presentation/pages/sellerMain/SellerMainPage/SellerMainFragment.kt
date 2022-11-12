package com.example.qiwi_front.presentation.pages.sellerMain.SellerMainPage

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerMainBinding
import com.example.qiwi_front.databinding.StatesBinding

class SellerMainFragment : FragmentBase<FragmentSellerMainBinding, SellerMainViewModel>() {

    companion object {
        fun newInstance() = SellerMainFragment()
    }

    override fun getViewModelClass(): Class<SellerMainViewModel> = SellerMainViewModel::class.java

    override fun getViewBinding(): FragmentSellerMainBinding = FragmentSellerMainBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerMainStates


}