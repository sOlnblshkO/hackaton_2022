package com.example.qiwi_front.presentation.pages.auth.slides.seller

import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerAuthBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.customerMainPage.CustomerMainFragment
import com.example.qiwi_front.presentation.pages.registration.seller.SellerRegistrationFragment
import com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage.SellerMainFragment
import com.example.qiwi_front.utils.enums.UserRoleEnum
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerAuthFragment @Inject constructor() :
    FragmentBase<FragmentSellerAuthBinding, SellerAuthViewModel>() {

    @Inject
    lateinit var sharedPreferencesUsage: SharedPreferencesUsage

    override fun setUpViews() {
        super.setUpViews()
        binding.sellerAuthButton.setOnClickListener {
            if (binding.sellerLoginInput.text.isNullOrEmpty() || binding.sellerPassInput.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener;
            }
            viewModel.authorize(
                binding.sellerLoginInput.text.toString(),
                binding.sellerPassInput.text.toString()
            )
        }
        binding.sellerAuthNotRegisterYet.setOnClickListener {
            addFragment(SellerRegistrationFragment.newInstance())
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.authorized.observe(this) {
            if (it?.token == null) {
                Toast.makeText(requireContext(), "Не удалось авторизоваться", Toast.LENGTH_SHORT)
                    .show()
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
        sharedPreferencesUsage.putBoolean(
            requireContext(),
            com.example.shared.consts.AppSettings.IsAuth,
            true
        )
        sharedPreferencesUsage.putStringSharedPreferences(
            requireContext(),
            com.example.shared.consts.AppSettings.UserRole,
            UserRoleEnum.Seller.name
        )
    }

    companion object {
        fun newInstance() = SellerAuthFragment()
    }

    override fun getViewModelClass(): Class<SellerAuthViewModel> = SellerAuthViewModel::class.java

    override fun getViewBinding(): FragmentSellerAuthBinding =
        FragmentSellerAuthBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerAuthStates

}