package com.example.domain.requests.code.getCode

import kotlinx.serialization.Serializable

@Serializable
data class GetCodeRequest(
    val PhoneNumber: String,
    val RequestId: String
)
