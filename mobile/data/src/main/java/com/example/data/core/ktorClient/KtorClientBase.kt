package com.example.data.core.ktorClient

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

open class KtorClientBase() {
    init {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }

            override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
            override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
        })

        try {
            // Install the all-trusting trust manager
            val sc: SSLContext =
                SSLContext.getInstance("SSL")

            sc.init(null, trustAllCerts, SecureRandom())

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
        }
        catch (ex: Exception) {}

        // Create all-trusting host name verifier
        val allHostsValid: HostnameVerifier = object : HostnameVerifier {
            override fun verify(hostname: String?, session: SSLSession?): Boolean {
                return true
            }
        }

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
    }

    companion object {
        lateinit var ktorHttpClient: HttpClient
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
            ktorHttpClient.post(urlRequest) {
                setBody(requestBody)
            }
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
            Log.d("GOT", httpResponse.body())
            response = httpResponse.body()
        } catch (ex: Exception) {
        }
        return response
    }
}