package com.example.data.services.code

import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.code.getCode.GetCodeRequest
import com.example.domain.requests.code.sendCode.SendCodeRequest
import com.example.domain.responses.code.getCode.GetCodeResponse
import com.example.domain.responses.code.sendCode.SendCodeResponse
import com.example.domain.responses.code.sendCode.SendCodeResult
import javax.inject.Inject

class CodeService @Inject constructor() : KtorClient() {

    suspend fun getCode(getCodeRequest: GetCodeRequest): GetCodeResponse? =
        postWithResponse<GetCodeResponse?>("sms/GetSms", getCodeRequest)

    suspend fun sendCode(sendCodeRequest: SendCodeRequest): SendCodeResponse? =
        postWithResponse("sms/checkSms", sendCodeRequest)

}