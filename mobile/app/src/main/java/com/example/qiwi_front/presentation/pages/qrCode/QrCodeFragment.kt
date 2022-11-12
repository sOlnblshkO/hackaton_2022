package com.example.qiwi_front.presentation.pages.qrCode

import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.example.domain.responses.code.sendCode.CodeToken
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentQrCodeBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.utils.contracts.PaymentData
import com.google.gson.Gson

class QrCodeFragment(
    val amount: String,
    val name: String,
    val legalName: String,
    val token: CodeToken?,
    val requestId: String?
) :
    FragmentBase<FragmentQrCodeBinding, QrCodeViewModel>() {

    val QRDimension = 4096

    override fun setUpViews() {
        super.setUpViews()
        binding.qrCodeAmountText.text = "Сумма $amount"
        binding.qrCodeNameText.text = "Магазин $name"
        binding.qrCodeLegalText.text = "ИП $legalName"

        val tempToQrCode =
            PaymentData(token?.value ?: "", amount, "qwe12", requestId.toString(), legalName, name)
        val gson = Gson()
        val qrCodeEncoder =
            QRGEncoder(gson.toJson(tempToQrCode), QRGContents.Type.TEXT, QRDimension)
        binding.qrCodePlaceImage.setImageBitmap(qrCodeEncoder.bitmap)
    }

    companion object {
        fun newInstance(
            amount: String,
            name: String,
            legalName: String,
            token: CodeToken?,
            requestId: String?
        ) =
            QrCodeFragment(
                amount, name, legalName, token, requestId
            )
    }

    override fun getViewModelClass(): Class<QrCodeViewModel> = QrCodeViewModel::class.java

    override fun getViewBinding(): FragmentQrCodeBinding =
        FragmentQrCodeBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.qrCodeState


}