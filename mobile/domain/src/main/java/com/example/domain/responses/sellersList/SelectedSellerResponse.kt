package com.example.domain.responses.sellersList

@kotlinx.serialization.Serializable
data class SelectedSellerResponse(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val legalName: String,
    val category: String,
    val description: String,
    val address: String
)
