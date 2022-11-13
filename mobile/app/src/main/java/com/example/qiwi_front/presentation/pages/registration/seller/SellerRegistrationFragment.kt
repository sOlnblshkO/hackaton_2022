package com.example.qiwi_front.presentation.pages.registration.seller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerRegistrationBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerRegistrationFragment @Inject constructor() :
    FragmentBase<FragmentSellerRegistrationBinding, SellerRegistrationViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.sellerRegistrToolbar.setNavigationOnClickListener {
            goBack()
        }
        setNotificationBarColor(requireContext().getColor(R.color.orange))
        binding.sellerRegistrButton.setOnClickListener {
            if (binding.address.text.isNullOrEmpty()
                || binding.name.text.isNullOrEmpty()
                || binding.category.text.isNullOrEmpty()
                || binding.description.text.isNullOrEmpty()
                || binding.legal.text.isNullOrEmpty()
                || binding.pass.text.isNullOrEmpty()
                || binding.phone.text.isNullOrEmpty()
            ) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.createNewSeller(
                "",
                binding.name.text.toString(),
                binding.legal.text.toString(),
                binding.category.text.toString(),
                binding.description.text.toString(),
                binding.address.text.toString(),
                binding.phone.text.toString(),
                binding.pass.text.toString()
                )
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.registered.observe(this) {
            goBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        setNotificationBarColor(requireContext().getColor(R.color.white))
    }

    companion object {
        fun newInstance() = SellerRegistrationFragment()
    }

    override fun getViewModelClass(): Class<SellerRegistrationViewModel> =
        SellerRegistrationViewModel::class.java

    override fun getViewBinding(): FragmentSellerRegistrationBinding =
        FragmentSellerRegistrationBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding? = binding.sellerRegistrState

}