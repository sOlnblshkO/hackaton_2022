package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.responses.sellersList.GetSellersResponse
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentShopsListBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.adapter.ShopListAdapter
import com.example.qiwi_front.presentation.pages.selectedShop.SelectedShopFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopsListFragment @Inject constructor() :
    FragmentBase<FragmentShopsListBinding, ShopsListViewModel>() {


    override fun setUpViews() {
        super.setUpViews()
        binding.shopListItemSearchInput.doOnTextChanged { text, start, before, count ->
            viewModel.getList(text.toString())
        }
        binding.shopListRecycler.setHasFixedSize(true)
        binding.shopListRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observeData() {
        super.observeData()
        viewModel.getList("")
        viewModel.sellers.observe(this) {
            if (it == null)
                return@observe
            binding.shopListRecycler.adapter =
                ShopListAdapter(layoutInflater, it, ::openSelectedShop)
        }
    }

    fun openSelectedShop(shopListItem: GetSellersResponse){
        addFragment(SelectedShopFragment.newInstance(shopListItem.id))
    }

    companion object {
        fun newInstance() = ShopsListFragment()
    }

    override fun getViewModelClass(): Class<ShopsListViewModel> = ShopsListViewModel::class.java

    override fun getViewBinding(): FragmentShopsListBinding =
        FragmentShopsListBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.shopListState


}