package com.finerioconnect.sdk.server

import com.finerioconnect.sdk.models.FCApiKey
import com.finerioconnect.sdk.models.FCEvents
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FinerioConnectApi {
    companion object {
        const val BASE_URL = "https://l8rini29ki.execute-api.us-east-2.amazonaws.com/v1/pfm/"
    }

    @Headers("Content-Type: text/plain")
    @POST("validate/")
    suspend fun validateApiKey(@Body() fcApiKey: FCApiKey): Response<Void>?

    @POST("events/")
    suspend fun sendEvent(
        @Header("apiKey") apiKey: String,
        @Body() fcEvents: FCEvents
    ): Response<Void>?
}