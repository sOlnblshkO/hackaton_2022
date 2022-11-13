package com.example.qiwi_front.presentation.pages.selectedShop

import android.app.AlertDialog
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.DialogEnterCodeBinding
import com.example.qiwi_front.databinding.FragmentSelectedShopBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.qrCode.QrCodeFragment
import com.example.qiwi_front.utils.extensions.ViewKeyboardHiderExtension.Companion.hideKeyBoard
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectedShopFragment @Inject constructor(val selectedShopId: Int) :
    FragmentBase<FragmentSelectedShopBinding, SelectedShopViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.createQrCodeButton.setOnClickListener {
            if (binding.amountInput.text == null || binding.amountInput.text!!.trim().isEmpty()) {
                Toast.makeText(requireContext(), "Введите сумму", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            it.hideKeyBoard()
            viewModel.createAndGetCode()
        }
        binding.root.setOnClickListener {
            it.hideKeyBoard()
        }

        binding.selectedShopToolbar.setNavigationOnClickListener {
            goBack()
        }

    }

    override fun observeData() {
        super.observeData()
        viewModel.loadData(selectedShopId)

        viewModel.loadedSelectedShop.observe(this) {
            if (it == null)
                return@observe
            binding.selectedShopToolbarText.setText(it.name)
            binding.shopLegalNameText.setText(it.legalName)
            binding.shopAddressText.setText(it.address)
            binding.shopCategoryText.setText(it.category)
            binding.shopDescriptionText.setText(it.description)
            if (it.avatarUrl.isEmpty())
                return@observe
            Handler(Looper.getMainLooper()).post {
                Picasso
                    .get()
                    .load(it.avatarUrl)
                    .into(binding.selectedShopImage)
            }
        }

        viewModel.gotCodeResponse.observe(this) {
            if (it == null)
                return@observe
            showDialog()
        }

        viewModel.sendCodeResponse.observe(this) {
            if (it != null && it.result.status?.value != null && it.result.status?.value == "CREATED") {
                addFragment(
                    QrCodeFragment.newInstance(
                        binding.amountInput.text.toString(),
                        viewModel.loadedSelectedShop.value!!.name,
                        viewModel.loadedSelectedShop.value!!.legalName,
                        it.result.token,
                        it.result.requestId
                    )
                )
            } else {
                Toast.makeText(requireContext(), "Неверный код", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDialog() {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        val enterCodeDialogBinding = DialogEnterCodeBinding.inflate(layoutInflater)
        enterCodeDialogBinding.enterCodeButton.setOnClickListener {
            it.hideKeyBoard()
            viewModel.sendCode(enterCodeDialogBinding.textInputEditText.text.toString())
            dialog?.dismiss()
        }
        enterCodeDialogBinding.enterCodeExitButton.setOnClickListener {
            dialog?.dismiss()
        }

        builder.setView(enterCodeDialogBinding.root)
        dialog = builder.show()
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