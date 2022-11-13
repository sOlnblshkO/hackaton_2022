package com.example.data.services.registration

import android.content.Context
import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.registration.RegistrationRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RegistrationService @Inject constructor(val ktorClient: KtorClient)  {

    suspend fun createCustomer(registrationRequest: RegistrationRequest)
        = ktorClient.post("customer/register", registrationRequest)

}