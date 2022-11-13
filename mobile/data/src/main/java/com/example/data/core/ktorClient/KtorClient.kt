package com.example.data.core.ktorClient

import android.content.Context
import android.util.Log
import com.example.data.services.auth.AuthService
import com.example.shared.consts.AppSettings
import com.example.shared.sharedPreferncesUsage.SharedPreferencesUsage
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.nio.charset.Charset
import javax.inject.Inject

private const val TIME_OUT = 60_000

open class KtorClient @Inject constructor(
    @ApplicationContext context: Context,
    var sharedPreferences: SharedPreferencesUsage
) : KtorClientBase() {

    var ktorHttpClient: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                coerceInputValues = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }

            }
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.ContentType, Charset.forName("UTF-8"))
            header(HttpHeaders.Authorization, "Bearer " + sharedPreferences.getStringSharedPreferences(context, AppSettings.Token))
            url {
                protocol = URLProtocol.HTTPS
                host = "10.16.0.126:7142"
            }
        }

        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, request ->
                when (cause) {
                    // ToDo обработка ошибок с серва
                }
            }
        }
    }

    suspend inline fun <reified T> get(
        url: String,
    ): T? {
        var response: T? = null

        try {
            val httpResponse: HttpResponse = ktorHttpClient.get(url)
            response = httpResponse.body()
        } catch (ex: Exception) {
        }
        return response
    }

    suspend inline fun <reified T> getWithParameters(
        url: String,
        list: List<Pair<String, String>>,
    ): T? {
        var response: T? = null

        try {
            val httpResponse = ktorHttpClient.get(url) {
                list.forEach {
                    parameter(it.first, it.second)
                }
            }
            response = httpResponse.body()
        } catch (ex: Exception) {
        }

        return response
    }

    suspend fun post(
        urlRequest: String, requestBody: Any,
    ) {

        try {
            var temp = ktorHttpClient.post(urlRequest) {
                setBody(requestBody)
            }
            print(temp)
        } catch (ex: Exception) {

        }
    }

    suspend inline fun <reified T> postWithResponse(
        urlRequest: String,
        requestBody: Any,
    ): T? {
        var response: T? = null


        try {
            val httpResponse = ktorHttpClient.post(urlRequest) {
                setBody(requestBody)
            }
            response = httpResponse.body()
        } catch (ex: Exception) {
        }
        return response
    }

}