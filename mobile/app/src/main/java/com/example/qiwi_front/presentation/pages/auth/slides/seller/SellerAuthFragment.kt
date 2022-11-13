package com.example.qiwi_front.presentation.pages.auth.slides.seller

import android.widget.Toast
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentSellerAuthBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.sellerMain.sellerMainPage.SellerMainFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerAuthFragment @Inject constructor() : FragmentBase<FragmentSellerAuthBinding, SellerAuthViewModel>() {

    override fun setUpViews() {
        super.setUpViews()
        binding.sellerAuthButton.setOnClickListener {
            if (binding.sellerLoginInput.text.isNullOrEmpty() || binding.sellerPassInput.text.isNullOrEmpty()) {
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

            addFragment(SellerMainFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = SellerAuthFragment()
    }

    override fun getViewModelClass(): Class<SellerAuthViewModel> = SellerAuthViewModel::class.java

    override fun getViewBinding(): FragmentSellerAuthBinding = FragmentSellerAuthBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.sellerAuthStates

}