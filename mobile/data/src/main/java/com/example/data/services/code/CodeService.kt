package com.example.data.services.code

import android.content.Context
import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.code.getCode.GetCodeRequest
import com.example.domain.requests.code.sendCode.SendCodeRequest
import com.example.domain.responses.code.getCode.GetCodeResponse
import com.example.domain.responses.code.sendCode.SendCodeResponse
import com.example.domain.responses.code.sendCode.SendCodeResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CodeService @Inject constructor(val ktorClient: KtorClient)  {

    suspend fun getCode(getCodeRequest: GetCodeRequest): GetCodeResponse? =
        ktorClient.postWithResponse<GetCodeResponse?>("sms/GetSms", getCodeRequest)

    suspend fun sendCode(sendCodeRequest: SendCodeRequest): SendCodeResponse? =
        ktorClient.postWithResponse("sms/checkSms", sendCodeRequest)

}