package com.finerioconnect.sdk.core.services

import com.finerioconnect.sdk.core.apikey.interfaces.OnApiKeyRepositoryListener
import com.finerioconnect.sdk.server.FinerioConnectApi
import com.finerioconnect.sdk.server.ServerImpl
import com.finerioconnect.sdk.models.FCApiKey

object ApiKeyService {
    private var mListener: OnApiKeyRepositoryListener? = null

    suspend fun validate(FCApiKey: FCApiKey) {
        val serverImpl = ServerImpl(FinerioConnectApi.BASE_URL, false)
        val api = serverImpl.api(FinerioConnectApi::class.java)
        val call = api.validateApiKey(FCApiKey)

        if (call != null) {
            when (call.code()) {
                200 -> mListener?.successApiKey()
                401 -> mListener?.failApiKey("Invalid client! Contact Finerio")
                404 -> mListener?.failApiKey("Client not exist! Please check your ApiKey")
                else -> {
                    mListener?.failApiKey("Server error response!")
                }
            }
        } else {
            mListener?.failApiKey("Server error response!")
        }
    }

    fun setListener(listener: OnApiKeyRepositoryListener?) {
        this.mListener = listener
    }
}