package com.example.data.services.bill

import android.content.Context
import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.bill.BillRequest
import com.example.domain.responses.bill.BillResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.statement.*
import javax.inject.Inject

class BillService @Inject constructor(val ktorClient: KtorClient)  {
    suspend fun makePayment(billRequest: BillRequest): BillResponse {
        val response = ktorClient.postWithResponse<HttpResponse>("bill/getbill", billRequest)
        return when (response!!.status.value) {
        in 200..299 -> return BillResponse(isSuccess = true)
            else -> return BillResponse(isSuccess = false)
        }
    }
}