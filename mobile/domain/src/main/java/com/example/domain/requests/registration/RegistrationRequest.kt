package com.example.domain.requests.registration

@kotlinx.serialization.Serializable
data class RegistrationRequest(
    val phone: String,
    val name: String,
    val surname: String,
    val password: String
)