package com.example.qiwi_front.presentation.pages.auth

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.AuthFragmentBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment @Inject constructor() : FragmentBase<AuthFragmentBinding, AuthViewModel>() {

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getViewBinding(): AuthFragmentBinding = AuthFragmentBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.authStates

}