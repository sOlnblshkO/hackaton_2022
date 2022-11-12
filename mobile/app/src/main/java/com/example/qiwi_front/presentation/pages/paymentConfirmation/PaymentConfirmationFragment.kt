package com.example.qiwi_front.presentation.pages.paymentConfirmation

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentPaymentConfirmationBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.utils.contracts.PaymentData

class PaymentConfirmationFragment(private val paymentInformation: PaymentData) : FragmentBase<FragmentPaymentConfirmationBinding, PaymentConfirmationViewModel>() {


    override fun setUpViews() {
        super.setUpViews()
        viewModel.loadData(paymentInformation)
    }

    override fun observeData() {
        super.observeData()
        viewModel.paymentData.observe(this)  {
            binding.paymentAmount.text = "250 р."
            binding.paymentUrName.text = "By koyash"
            binding.paymentName.text = "Крутое заведение"
        }
    }

    companion object {
        fun newInstance(paymentInformation: PaymentData) = PaymentConfirmationFragment(paymentInformation)
    }

    override fun getViewModelClass() = PaymentConfirmationViewModel::class.java

    override fun getViewBinding(): FragmentPaymentConfirmationBinding =
        FragmentPaymentConfirmationBinding.inflate(layoutInflater)


    override fun getStateBinding(): StatesBinding  = binding.paymentConfirmationState
}
