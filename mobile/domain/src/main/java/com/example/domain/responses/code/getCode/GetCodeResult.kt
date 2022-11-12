package com.example.domain.responses.code.getCode

@kotlinx.serialization.Serializable
data class GetCodeResult(
    val requestId: String,
    val status: CodeStatus
)