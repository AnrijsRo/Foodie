package com.example.foodie.data.domain.repository.token

import com.example.foodie.BuildConfig


class TokenRepositoryImpl : TokenRepository {

    override fun getAuthToken(): String {
        //typically this would be stored locally and this repo
        // would fetch the token from wherever it's stored
        return BuildConfig.API_TOKEN
    }
}