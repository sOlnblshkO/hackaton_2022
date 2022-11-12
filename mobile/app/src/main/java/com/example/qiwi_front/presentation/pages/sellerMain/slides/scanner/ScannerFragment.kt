package com.example.qiwi_front.presentation.pages.sellerMain.slides.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentScannerBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.google.zxing.BarcodeFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScannerFragment @Inject constructor() : FragmentBase<FragmentScannerBinding, ScannerViewModel>()  {

    companion object {
        fun newInstance() = ScannerFragment()
    }

    override fun setUpViews() {
        super.setUpViews()

        requestCameraPermission()

        val scannerView = binding.scannerView

        val codeScanner = CodeScanner(requireContext(), scannerView)

        codeScanner.formats = listOf(BarcodeFormat.QR_CODE) // list of type BarcodeFormat,

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            Log.i("sdf", it.text)
        }
        codeScanner.startPreview()
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                1
            )
        }
    }

    override fun getViewModelClass(): Class<ScannerViewModel> =
        ScannerViewModel::class.java

    override fun getViewBinding(): FragmentScannerBinding =
        FragmentScannerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding =
        binding.scannerStates
}