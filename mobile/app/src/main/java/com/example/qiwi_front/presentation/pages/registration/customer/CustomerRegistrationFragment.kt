package com.example.qiwi_front.presentation.pages.registration.customer

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.qiwi_front.BuildConfig
import com.example.qiwi_front.R
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerRegistrationBinding
import com.example.qiwi_front.databinding.StatesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerRegistrationFragment @Inject constructor():
    FragmentBase<FragmentCustomerRegistrationBinding, CustomerRegistrationViewModel>() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setUpViews() {
        super.setUpViews()
        setNotificationBarColor(requireContext().getColor(R.color.orange))
        binding.customerRegistrToolbar.setNavigationOnClickListener {
            setNotificationBarColor(requireContext().getColor(R.color.white))
            goBack()
        }
        if (BuildConfig.DEBUG){
            binding.phone.setText("78000000008")
        }
        binding.customerRegsitrButton.setOnClickListener {
            viewModel.register(
                binding.phone.text.toString(),
                binding.name.text.toString(),
                binding.pass.text.toString(),
                binding.surname.text.toString()
            )
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.registered.observe(this) {
            Toast.makeText(requireContext(), "Регистрация успешна!", Toast.LENGTH_SHORT).show()
            goBack()
        }
    }

    companion object {
        fun newInstance() = CustomerRegistrationFragment()
    }

    override fun getViewModelClass(): Class<CustomerRegistrationViewModel> =
        CustomerRegistrationViewModel::class.java

    override fun getViewBinding(): FragmentCustomerRegistrationBinding = FragmentCustomerRegistrationBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding? = binding.customerRegisterState

}