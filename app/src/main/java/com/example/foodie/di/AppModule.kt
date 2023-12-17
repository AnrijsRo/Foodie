package com.example.foodie.di


import android.content.Context
import androidx.room.Room
import com.example.foodie.BuildConfig
import com.example.foodie.data.domain.persistent.FOODIE_DATABASE
import com.example.foodie.data.domain.persistent.FoodieDatabase
import com.example.foodie.data.domain.repository.recipe.RecipeRepository
import com.example.foodie.data.domain.repository.recipe.RecipeRepositoryImpl
import com.example.foodie.data.domain.repository.token.TokenRepository
import com.example.foodie.data.domain.repository.token.TokenRepositoryImpl
import com.example.foodie.data.remote.api.RecipeApi
import com.example.foodie.data.remote.interceptor.HeaderInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FoodieDatabase {
        return Room.databaseBuilder(context, FoodieDatabase::class.java, FOODIE_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)

    @Singleton
    @Provides
    fun provideTokenRepository(): TokenRepository = TokenRepositoryImpl()

    @Singleton
    @Provides
    fun provideRecipeRepository(recipeApi: RecipeApi, database: FoodieDatabase): RecipeRepository =
        RecipeRepositoryImpl(recipeApi = recipeApi, foodieDatabase = database)

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