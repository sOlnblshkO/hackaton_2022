package com.example.domain.requests.registration

@kotlinx.serialization.Serializable
data class SellerRegistrationRequest(
    val avatarUrl: String,
    val name: String,
    val legalName: String,
    val category: String,
    val description: String,
    val address: String,
    val phone: String,
    val password: String,
)