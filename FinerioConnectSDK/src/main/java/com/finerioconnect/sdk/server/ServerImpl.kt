package com.finerioconnect.sdk.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServerImpl(private val baseUrl: String, private val isDebug: Boolean) : Server {

    companion object {

        private const val READ_TIME_OUT: Long = 60
        private const val CONNECTION_TIME_OUT: Long = 60

    }

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = if (isDebug) Level.BODY else Level.NONE

        OkHttpClient.Builder()
            .addInterceptor(this)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    override fun<T: FinerioConnectApi> api(apiClient: Class<T>): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .run { create(apiClient) }

}