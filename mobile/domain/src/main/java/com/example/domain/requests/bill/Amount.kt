package com.example.domain.requests.bill

@kotlinx.serialization.Serializable
data class Amount(
    val currency: String ,
    val value: String
)
