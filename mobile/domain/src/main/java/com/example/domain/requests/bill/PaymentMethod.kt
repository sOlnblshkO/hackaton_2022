package com.example.domain.requests.bill

@kotlinx.serialization.Serializable
data class PaymentMethod(
    val type: String = "TOKEN",
    val paymentToken: String,
)
