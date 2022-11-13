package com.example.data.services.registration

import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.registration.CustomerRegistrationRequest
import com.example.domain.requests.registration.SellerRegistrationRequest
import javax.inject.Inject

class RegistrationService @Inject constructor(val ktorClient: KtorClient)  {

    suspend fun createCustomer(customerRegistrationRequest: CustomerRegistrationRequest)
        = ktorClient.post("customer/register", customerRegistrationRequest)

    suspend fun createSeller(sellerRegistrationRequest: SellerRegistrationRequest)
        = ktorClient.post("seller/register", sellerRegistrationRequest)

}