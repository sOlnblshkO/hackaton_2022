package com.example.qiwi_front.presentation.pages.auth

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.AuthFragmentBinding
import com.example.qiwi_front.presentation.pages.auth.screenSliderPage.ScreenSlidePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AuthFragment @Inject constructor() : FragmentBase<AuthFragmentBinding, AuthViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.authSlider.adapter = ScreenSlidePagerAdapter(requireActivity())
        binding.authDots.attachTo(binding.authSlider)
        setNotificationBarColor(Color.WHITE)
    }

    companion object {
        fun newInstance() = AuthFragment()
    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDestroy() {
        super.onDestroy()
        setNotificationBarColor(requireContext().getColor(R.color.purple_700))
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getViewBinding(): AuthFragmentBinding = AuthFragmentBinding.inflate(layoutInflater)

    override fun getStateBinding() = binding.authStates

}