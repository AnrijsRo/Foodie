package com.example.foodie.data.domain.repository.token


interface TokenRepository {
    fun getAuthToken(): String
}