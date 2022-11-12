package com.example.domain.requests.code.sendCode

@kotlinx.serialization.Serializable
data class SendCodeRequest(
    val requestId: String,
    val smsCode: String
)