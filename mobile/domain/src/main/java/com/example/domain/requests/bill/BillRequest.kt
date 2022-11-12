package com.example.domain.requests.bill

@kotlinx.serialization.Serializable
data class BillRequest(
    val amount: Amount,
    val paymentMethod: PaymentMethod,
    val customer: Customer,
    val requestId: String
)