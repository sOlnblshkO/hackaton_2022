package com.example.qiwi_front.presentation.pages.qrCode

import android.text.Editable
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentQrCodeBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.utils.contracts.PaymentData
import com.google.gson.Gson

class QrCodeFragment(val amount: String, val name: String, val legalName: String) :
    FragmentBase<FragmentQrCodeBinding, QrCodeViewModel>() {

    val QRDimension = 1024

    override fun setUpViews() {
        super.setUpViews()
        binding.qrCodeAmountText.text = "Сумма $amount"
        binding.qrCodeNameText.text = "Магазин $name"
        binding.qrCodeLegalText.text = "ИП $legalName"
            //TODO() Переписать на проброс модели в конструктор
        val paymentData = PaymentData("Ого!!! Это же токен!???")
        val gson = Gson()
        val qrCodeEncoder =
            QRGEncoder(gson.toJson(paymentData), QRGContents.Type.TEXT, QRDimension)
        binding.qrCodePlaceImage.setImageBitmap(qrCodeEncoder.bitmap)
    }

    companion object {
        fun newInstance(amount: String, name: String, legalName: String) = QrCodeFragment(
            amount, name, legalName
        )
    }

    override fun getViewModelClass(): Class<QrCodeViewModel> = QrCodeViewModel::class.java

    override fun getViewBinding(): FragmentQrCodeBinding =
        FragmentQrCodeBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.qrCodeState


}