package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.core.shared.FileLoader
import com.example.qiwi_front.databinding.ShopListItemBinding
import com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.contracts.ShopListItem

class ShopListAdapter(
    val layoutInflater: LayoutInflater,
    val shopItems: List<ShopListItem>,
    val fileLoader: FileLoader,
    val openSelectedShop: (ShopListItem) -> Unit
) :
    RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    class ShopListViewHolder(val view: ShopListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        return ShopListViewHolder(ShopListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        holder.view.shopListItemName.text = shopItems[position].name
//        if (shopItems[position].url.isEmpty())
//            return
//
//        var bitmap = fileLoader.getBitmap(shopItems[position].url)
//        holder.view.shopListItemImage.setImageBitmap(bitmap)
        holder.view.root.setOnClickListener {
            openSelectedShop(shopItems[position])
        }
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }


}