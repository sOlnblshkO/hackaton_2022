package com.example.data.core.ktorClient

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.nio.charset.Charset

private const val TIME_OUT = 60_000

open class KtorClient() : KtorClientBase() {

    init {
        ktorHttpClient = HttpClient(Android) {
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
    }

}