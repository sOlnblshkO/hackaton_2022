package com.example.qiwi_front.presentation.pages.auth.slides.customer

import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.customerMainPage.CustomerMainFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerAuthFragment @Inject constructor() :
    FragmentBase<FragmentCustomerBinding, CustomerAuthViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.customerAuthButton.setOnClickListener {
            if (binding.customerLoginInput.text.isNullOrEmpty() || binding.customerPasswordInput.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener;
            }
            viewModel.authorize()
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.authorized.observe(this) {
            if (!it)
                return@observe

            replaceFragment(CustomerMainFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = CustomerAuthFragment()
    }

    override fun getViewModelClass(): Class<CustomerAuthViewModel> =
        CustomerAuthViewModel::class.java

    override fun getViewBinding(): FragmentCustomerBinding =
        FragmentCustomerBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerAuthStates


}