package com.example.qiwi_front.presentation.pages.sellerMain.slides.profile

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentProfileBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor() : FragmentBase<FragmentProfileBinding, ProfileViewModel>()  {


    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.profileStates
}