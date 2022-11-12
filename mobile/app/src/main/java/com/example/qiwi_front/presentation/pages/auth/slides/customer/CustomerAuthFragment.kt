package com.example.qiwi_front.presentation.pages.auth.slides.customer

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerAuthFragment @Inject constructor() :
    FragmentBase<FragmentCustomerBinding, CustomerAuthViewModel>() {

    companion object{
        fun newInstance() = CustomerAuthFragment()
    }

    override fun getViewModelClass(): Class<CustomerAuthViewModel> = CustomerAuthViewModel::class.java

    override fun getViewBinding(): FragmentCustomerBinding = FragmentCustomerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerAuthStates


}