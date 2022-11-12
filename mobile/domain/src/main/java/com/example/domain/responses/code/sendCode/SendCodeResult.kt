package com.example.domain.responses.code.sendCode

import com.example.domain.responses.code.getCode.CodeStatus

@kotlinx.serialization.Serializable
data class SendCodeResult(
    val requestId: String?,
    val status: CodeStatus?,
    val token: CodeToken?
)
