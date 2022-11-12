package com.example.domain.responses.code.sendCode

@kotlinx.serialization.Serializable
data class CodeToken(
    val value: String,
    val expireDate: String
)