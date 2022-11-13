package com.example.domain.responses.sellersList

@kotlinx.serialization.Serializable
data class GetSellersResponse(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val category: String
)