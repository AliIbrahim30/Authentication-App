package com.example.authentication_app.di

import com.example.authentication_app.data.remote.ApiService
import com.example.authentication_app.data.remote.AuthInterceptor
import com.example.authentication_app.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiKey(): String="reqres-free-v1"

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKey: String): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKey = apiKey))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}