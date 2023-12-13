package com.example.foodie.di


import com.example.foodie.BuildConfig
import com.example.foodie.data.domain.repository.token.TokenRepository
import com.example.foodie.data.remote.interceptor.HeaderInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        tokenRepository: TokenRepository,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(
            buildClient(tokenRepository)
        ).build()

    private fun buildClient(
        tokenRepository: TokenRepository
    ): OkHttpClient {
        val headerInterceptor = HeaderInterceptor(tokenRepository)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient()
            .newBuilder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}