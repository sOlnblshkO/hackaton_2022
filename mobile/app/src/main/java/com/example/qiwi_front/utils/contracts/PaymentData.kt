package com.example.qiwi_front.utils.contracts

data class PaymentData(
    val token: String,
    val amount: String,
    val customerAccount: String,
    val requestId: String,
    val legalizeUser: String,
    val shopName: String,
)