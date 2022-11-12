package com.example.qiwi_front.presentation.pages.customerMain.slides.profile

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerProfileBinding
import com.example.qiwi_front.databinding.StatesBinding


class ProfileFragment : FragmentBase<FragmentCustomerProfileBinding, ProfileViewModel>() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getViewBinding(): FragmentCustomerProfileBinding =
        FragmentCustomerProfileBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerProfileState
}