package com.example.authentication_app.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest =chain.request().newBuilder()
            .addHeader("x-api-key",apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}