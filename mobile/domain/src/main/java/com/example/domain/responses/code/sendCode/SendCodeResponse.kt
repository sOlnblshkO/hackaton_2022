package com.example.domain.responses.code.sendCode

@kotlinx.serialization.Serializable
data class SendCodeResponse(
    val id: Int,
    val status: Int,
    val result: SendCodeResult
)