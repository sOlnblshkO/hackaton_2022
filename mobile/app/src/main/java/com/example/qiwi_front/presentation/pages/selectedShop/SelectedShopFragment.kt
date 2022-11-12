package com.example.qiwi_front.presentation.pages.selectedShop

import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSelectedShopBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.qrCode.QrCodeFragment
import com.example.qiwi_front.utils.extensions.ViewKeyboardHiderExtension.Companion.hideKeyBoard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedShopFragment(val selectedShopId: Int) :
    FragmentBase<FragmentSelectedShopBinding, SelectedShopViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.createQrCodeButton.setOnClickListener {
            if (binding.amountInput.text == null || binding.amountInput.text!!.trim().isEmpty()){
                Toast.makeText(requireContext(), "Введите сумму", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            it.hideKeyBoard()
            addFragment(
                QrCodeFragment.newInstance(
                    binding.amountInput.text.toString(),
                    viewModel.loadedShopData.value!!.Name,
                    viewModel.loadedShopData.value!!.LegalName,
            ))
        }
        binding.root.setOnClickListener {
            it.hideKeyBoard()
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.loadData(selectedShopId)
        viewModel.loadedShopData.observe(this) {
            binding.shopNameText.text = it.Name
            binding.shopLegalNameText.text = it.LegalName
        }
    }

    companion object {
        fun newInstance(id: Int) = SelectedShopFragment(id)
    }

    override fun getViewModelClass(): Class<SelectedShopViewModel> =
        SelectedShopViewModel::class.java

    override fun getViewBinding(): FragmentSelectedShopBinding =
        FragmentSelectedShopBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.selectedShopState

}