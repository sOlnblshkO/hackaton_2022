package com.example.data.services.bill

import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.bill.BillRequest
import com.example.domain.responses.bill.BillResponse
import io.ktor.client.statement.*
import javax.inject.Inject

class BillService @Inject constructor() : KtorClient() {
    suspend fun makePayment(billRequest: BillRequest): BillResponse {
        val response = postWithResponse<HttpResponse>("bill/getbill", billRequest)
        return when (response!!.status.value) {
        in 200..299 -> return BillResponse(isSuccess = true)
            else -> return BillResponse(isSuccess = false)
        }
    }
}