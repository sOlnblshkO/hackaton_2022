package com.example.qiwi_front.presentation.pages.auth

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.AuthFragmentBinding
import com.example.qiwi_front.databinding.StatesBinding

class AuthFragment : FragmentBase<AuthFragmentBinding, AuthViewModel>() {
    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getViewBinding(): AuthFragmentBinding = AuthFragmentBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.authStates

}