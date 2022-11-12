package com.example.domain.responses.code.getCode

import kotlinx.serialization.Serializable

@Serializable
data class GetCodeResponse(
    val result: GetCodeResult?,
    val id: Int?,
    val exception: String?,
    val status: Int?,
)