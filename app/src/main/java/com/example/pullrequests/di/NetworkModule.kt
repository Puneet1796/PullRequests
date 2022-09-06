package com.example.pullrequests.di

import com.example.pullrequests.BuildConfig
import com.example.pullrequests.network.HeaderInterceptor
import com.example.pullrequests.network.NetworkInterface
import com.example.pullrequests.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Logger
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    @Header
    fun providesHeaderInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    @Provides
    fun providesOkHttpClient(
        @Header headerInterceptor: Interceptor,
        @Logger loggerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(headerInterceptor)
            .addNetworkInterceptor(loggerInterceptor)
            .build()
    }

    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesNetworkInterface(
        retrofit: Retrofit
    ): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }
}
