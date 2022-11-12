package com.example.qiwi_front.presentation.pages.scanner

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentProfileBinding
import com.example.qiwi_front.databinding.FragmentScannerBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.auth.AuthFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScannerFragment @Inject constructor() : FragmentBase<FragmentScannerBinding, ScannerViewModel>()  {


    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun getViewModelClass(): Class<ScannerViewModel> = ScannerViewModel::class.java

    override fun getViewBinding(): FragmentScannerBinding = FragmentScannerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.scannerStates
}