package com.example.data.services.auth

import android.content.Context
import com.example.data.core.ktorClient.KtorClient
import com.example.domain.requests.auth.AuthRequest
import com.example.domain.responses.auth.AuthResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthService @Inject constructor(val ktorClient: KtorClient)  {

    suspend fun authorize(authRequest: AuthRequest): AuthResponse? =
        ktorClient.postWithResponse("authenticate/login", authRequest)
}