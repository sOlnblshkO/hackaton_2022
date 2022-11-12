package com.example.qiwi_front.presentation.pages.auth.slides.seller

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerAuthFragment @Inject constructor() : FragmentBase<FragmentSellerBinding, SellerAuthViewModel>() {

    companion object {
        fun newInstance() = SellerAuthFragment()
    }

    override fun getViewModelClass(): Class<SellerAuthViewModel> = SellerAuthViewModel::class.java

    override fun getViewBinding(): FragmentSellerBinding = FragmentSellerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerAuthStates

}