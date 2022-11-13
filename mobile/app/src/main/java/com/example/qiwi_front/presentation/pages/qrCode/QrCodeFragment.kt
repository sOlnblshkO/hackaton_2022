package com.example.qiwi_front.presentation.pages.qrCode

import android.app.AlertDialog
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.example.domain.responses.code.sendCode.CodeToken
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentQrCodeBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.customerMainPage.CustomerMainFragment
import com.example.qiwi_front.utils.contracts.PaymentData
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import com.google.gson.Gson
import com.microsoft.signalr.HubConnectionBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class QrCodeFragment @Inject constructor(
    val amount: String,
    val name: String,
    val legalName: String,
    val token: CodeToken?,
    val requestId: String?
) : FragmentBase<FragmentQrCodeBinding, QrCodeViewModel>() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

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
        binding.qrCodePlaceImage.setImageBitmap(qrCodeEncoder.getBitmap(0))
        binding.qrCodeToolbar.setNavigationOnClickListener {
            goBack()
        }
        val hubConnection =
            HubConnectionBuilder.create("http://10.16.0.126:5187/hubs/endPayment").build()
        hubConnection.on(
            "$requestId", { message: String ->
                runBlocking {
                    launch(Dispatchers.Main) {
                        showDialog()
                    }
                }
            }, String::class.java
        )
        hubConnection.start().blockingAwait()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Успех!")
        builder.setMessage("Операция прошла успешно!")
        builder.setPositiveButton("Ок") { dialogInterface, i ->
            replaceFragment(CustomerMainFragment.newInstance())
        }
        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        fun newInstance(
            amount: String, name: String, legalName: String, token: CodeToken?, requestId: String?
        ) = QrCodeFragment(
            amount, name, legalName, token, requestId
        )
    }

    override fun getViewModelClass(): Class<QrCodeViewModel> = QrCodeViewModel::class.java

    override fun getViewBinding(): FragmentQrCodeBinding =
        FragmentQrCodeBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.qrCodeState


}