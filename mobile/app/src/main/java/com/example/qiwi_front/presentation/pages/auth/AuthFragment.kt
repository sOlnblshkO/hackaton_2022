package com.example.qiwi_front.presentation.pages.auth

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.AuthFragmentBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.auth.screenSliderPage.ScreenSlidePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment @Inject constructor() : FragmentBase<AuthFragmentBinding, AuthViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.authSlider.adapter = ScreenSlidePagerAdapter(requireActivity())
        binding.authDots.attachTo(binding.authSlider)
    }

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getViewBinding(): AuthFragmentBinding = AuthFragmentBinding.inflate(layoutInflater)

    override fun getStateBinding() = binding.authStates

}