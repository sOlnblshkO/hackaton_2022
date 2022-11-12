package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.core.shared.FileLoader
import com.example.qiwi_front.base.fragment.FragmentBase
import com.example.qiwi_front.databinding.FragmentShopsListBinding
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.adapter.ShopListAdapter
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.contracts.ShopListItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopsListFragment @Inject constructor() :
    FragmentBase<FragmentShopsListBinding, ShopsListViewModel>() {

    @Inject
    lateinit var fileLoader: FileLoader

    override fun setUpViews() {
        super.setUpViews()
        binding.shopListItemSearchInput.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty() || text.length < 3) {
                binding.shopListRecycler.adapter =
                    ShopListAdapter(layoutInflater, viewModel.shopItems, fileLoader)
            } else {
                binding.shopListRecycler.adapter =
                    ShopListAdapter(
                        layoutInflater,
                        viewModel.shopItems.filter{ e -> e.name.contains(text) },
                        fileLoader
                    )
            }
        }
        binding.shopListRecycler.adapter =
            ShopListAdapter(layoutInflater, viewModel.shopItems, fileLoader)
        binding.shopListRecycler.setHasFixedSize(true)
        binding.shopListRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observeData() {
        super.observeData()
        viewModel.load()
    }

    companion object {
        fun newInstance() = ShopsListFragment()
    }

    override fun getViewModelClass(): Class<ShopsListViewModel> = ShopsListViewModel::class.java

    override fun getViewBinding(): FragmentShopsListBinding =
        FragmentShopsListBinding.inflate(layoutInflater)

    override fun getStateBinding(): StatesBinding = binding.shopListState


}