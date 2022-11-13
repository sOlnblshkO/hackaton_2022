package com.example.domain.requests.auth

@kotlinx.serialization.Serializable
data class AuthRequest(
    val phoneNumber: String,
    val password: String
)
