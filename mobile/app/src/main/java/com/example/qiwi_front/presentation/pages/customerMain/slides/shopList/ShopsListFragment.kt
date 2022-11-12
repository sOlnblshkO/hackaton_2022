package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList

import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentShopsListBinding
import com.example.qiwi_front.databinding.StatesBinding

class ShopsListFragment : FragmentBase<FragmentShopsListBinding, ShopsListViewModel>() {

    companion object {
        fun newInstance() = ShopsListFragment()
    }

    override fun getViewModelClass(): Class<ShopsListViewModel> = ShopsListViewModel::class.java

    override fun getViewBinding(): FragmentShopsListBinding = FragmentShopsListBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.shopListState


}