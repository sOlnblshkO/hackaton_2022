package com.example.domain.responses.auth

@kotlinx.serialization.Serializable
data class AuthResponse(
    val token: String
)
