package com.example.data.services.sellers

import android.content.Context
import com.example.data.core.ktorClient.KtorClient
import com.example.domain.responses.sellersList.GetSellersResponse
import com.example.domain.responses.sellersList.SelectedSellerResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SellersService @Inject constructor()  {
    @Inject
    lateinit var ktorClient: KtorClient

    suspend fun getSellers(searchSub: String) =
        ktorClient.get<List<GetSellersResponse>>("seller/getsellers?subStringQuery=$searchSub")

    suspend fun getSelectedSeller(id: Int): SelectedSellerResponse? =
        ktorClient.get<SelectedSellerResponse>("seller/getsellerinfo/$id");
}