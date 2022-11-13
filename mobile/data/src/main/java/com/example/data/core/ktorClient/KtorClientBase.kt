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
    
}