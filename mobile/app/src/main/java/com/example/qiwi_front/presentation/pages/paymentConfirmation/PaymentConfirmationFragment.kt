package com.example.qiwi_front.presentation.pages.paymentConfirmation

import android.app.AlertDialog
import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentPaymentConfirmationBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage.SellerMainFragment
import com.example.qiwi_front.utils.contracts.PaymentData
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import com.microsoft.signalr.HubConnectionBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class PaymentConfirmationFragment @Inject constructor(private val paymentInformation: PaymentData) :
    FragmentBase<FragmentPaymentConfirmationBinding, PaymentConfirmationViewModel>() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage


    override fun setUpViews() {
        super.setUpViews()
        viewModel.loadData(paymentInformation)
        binding.acceptPaymentBtn.setOnClickListener {
            viewModel.acceptPayment(paymentInformation)
        }
        val hubConnection =
            HubConnectionBuilder.create("http://10.16.0.126:5187/hubs/endPayment").build()
        hubConnection.on(
            paymentInformation.requestId, { message: String ->
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
            replaceFragment(SellerMainFragment.newInstance())
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun observeData() {
        super.observeData()
        viewModel.paymentData.observe(this) {
            binding.paymentAmount.text = it.amount
            binding.paymentUrName.text = it.legalizeUser
            binding.paymentName.text = it.shopName
        }

        viewModel.paymentAccepted.observe(this) {
            if (it == null || !it) {
                Toast.makeText(requireContext(), "Оплата не прошла", Toast.LENGTH_SHORT).show()
                return@observe
            }
            Toast.makeText(requireContext(), "Оплата прошла успешно", Toast.LENGTH_SHORT).show()
            replaceFragment(SellerMainFragment.newInstance())
        }
    }

    companion object {
        fun newInstance(paymentInformation: PaymentData) =
            PaymentConfirmationFragment(paymentInformation)
    }

    override fun getViewModelClass() = PaymentConfirmationViewModel::class.java

    override fun getViewBinding(): FragmentPaymentConfirmationBinding =
        FragmentPaymentConfirmationBinding.inflate(layoutInflater)


    override fun getStateBinding(): StatesBinding = binding.paymentConfirmationState
}
