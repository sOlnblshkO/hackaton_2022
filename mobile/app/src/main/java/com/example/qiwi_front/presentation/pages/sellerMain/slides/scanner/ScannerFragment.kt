package com.example.qiwi_front.presentation.pages.sellerMain.slides.scanner

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentScannerBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScannerFragment @Inject constructor() : FragmentBase<FragmentScannerBinding, ScannerViewModel>()  {

    companion object {
        fun newInstance() = ScannerFragment()
    }

    override fun getViewModelClass(): Class<ScannerViewModel> =
        ScannerViewModel::class.java

    override fun getViewBinding(): FragmentScannerBinding =
        FragmentScannerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding =
        binding.scannerStates
}