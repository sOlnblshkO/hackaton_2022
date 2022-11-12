package com.example.qiwi_front.presentation.pages.customerMain.slides.profile

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerProfileBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor() : FragmentBase<FragmentCustomerProfileBinding, ProfileViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.customerProfileExitButton.setOnClickListener {
            viewModel.exit()
            replaceFragment(AuthFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getViewBinding(): FragmentCustomerProfileBinding =
        FragmentCustomerProfileBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerProfileState
}