package com.example.foodie.data.remote.interceptor

import com.example.foodie.data.domain.repository.token.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val tokenRepository: TokenRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Authorization Bearer", tokenRepository.getAuthToken())

        return chain.proceed(builder.build())
    }
}