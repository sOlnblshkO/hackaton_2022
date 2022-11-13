package com.example.qiwi_front.presentation.pages.auth.slides.customer

import android.widget.Toast
import com.example.qiwi_front.BuildConfig
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentCustomerAuthBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.customerMainPage.CustomerMainFragment
import com.example.qiwi_front.presentation.pages.registration.customer.CustomerRegistrationFragment
import com.example.shared.consts.AppSettings
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerAuthFragment @Inject constructor() :
    FragmentBase<FragmentCustomerAuthBinding, CustomerAuthViewModel>() {

    @Inject
    lateinit var sharedPreferencesUsage: com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage

    override fun setUpViews() {
        super.setUpViews()
        binding.customerAuthButton.setOnClickListener {
            if (binding.customerLoginInput.text.isNullOrEmpty() || binding.customerPasswordInput.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener;
            }
            viewModel.authorize(
                binding.customerLoginInput.text.toString(),
                binding.customerPasswordInput.text.toString()
            )
        }
        binding.authCustomerNotHaveAccYetText.setOnClickListener{
            addFragment(CustomerRegistrationFragment.newInstance())
        }

        if (BuildConfig.DEBUG){
            binding.customerLoginInput.setText("78000000008")
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.authorized.observe(this) {
            if (it?.token == null) {
                Toast.makeText(requireContext(), "Не удалось авторизоваться", Toast.LENGTH_SHORT).show()
                return@observe
            }

            saveUser(it.token)
            replaceFragment(CustomerMainFragment.newInstance())
        }
    }

    private fun saveUser(token: String) {
        sharedPreferencesUsage.putStringSharedPreferences(
            requireContext(),
            com.example.shared.consts.AppSettings.Token,
            token
        )
        sharedPreferencesUsage.putBoolean(requireContext(), com.example.shared.consts.AppSettings.IsAuth, true)
        sharedPreferencesUsage.putStringSharedPreferences(
            requireContext(),
            com.example.shared.consts.AppSettings.UserRole,
            UserRoleEnum.Customer.name
        )
    }

    companion object {
        fun newInstance() = CustomerAuthFragment()
    }

    override fun getViewModelClass(): Class<CustomerAuthViewModel> =
        CustomerAuthViewModel::class.java

    override fun getViewBinding(): FragmentCustomerAuthBinding =
        FragmentCustomerAuthBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.customerAuthStates


}