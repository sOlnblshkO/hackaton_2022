package com.example.qiwi_front.presentation.pages.paymentConfirmation

import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentPaymentConfirmationBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage.SellerMainFragment
import com.example.qiwi_front.utils.contracts.PaymentData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentConfirmationFragment @Inject constructor(private val paymentInformation: PaymentData) : FragmentBase<FragmentPaymentConfirmationBinding, PaymentConfirmationViewModel>() {


    override fun setUpViews() {
        super.setUpViews()
        viewModel.loadData(paymentInformation)
        binding.acceptPaymentBtn.setOnClickListener {
            viewModel.acceptPayment(paymentInformation)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.paymentData.observe(this)  {
            binding.paymentAmount.text = it.amount
            binding.paymentUrName.text = it.legalizeUser
            binding.paymentName.text = it.shopName
        }

        viewModel.paymentAccepted.observe(this) {
            if (it == null || !it){
                Toast.makeText(requireContext(), "Оплата не прошла", Toast.LENGTH_SHORT).show()
                return@observe
            }
            Toast.makeText(requireContext(), "Оплата прошла успешно", Toast.LENGTH_SHORT).show()
            replaceFragment(SellerMainFragment.newInstance())
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
