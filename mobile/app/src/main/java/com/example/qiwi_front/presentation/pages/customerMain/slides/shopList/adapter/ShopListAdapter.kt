package com.example.qiwi_front.presentation.pages.customerMain.slides.shopList.adapter

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.responses.sellersList.GetSellersResponse
import com.example.qiwi_front.databinding.ShopListItemBinding
import com.squareup.picasso.Picasso

class ShopListAdapter(
    val layoutInflater: LayoutInflater,
    val shopItems: List<GetSellersResponse>,
    val openSelectedShop: (GetSellersResponse) -> Unit,
) :
    RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    class ShopListViewHolder(val view: ShopListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        return ShopListViewHolder(ShopListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        holder.view.shopListItemName.text = shopItems[position].name
        holder.view.shopListDescriptionText.text = shopItems[position].category
        holder.view.root.setOnClickListener {
            openSelectedShop(shopItems[position])
        }

        if (shopItems[position].avatarUrl.isEmpty())
            return

        Handler(Looper.getMainLooper()).post {
            Picasso
                .get()
                .load(shopItems[position].avatarUrl)
                .into(holder.view.shopListItemImage)
        }

    }

    override fun getItemCount(): Int {
        return shopItems.size
    }


}