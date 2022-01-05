package com.finerioconnect.sdk.core.apikey.repositories

import android.util.Log
import com.finerioconnect.sdk.core.apikey.interfaces.ApiKeyRepository
import com.finerioconnect.sdk.core.apikey.interfaces.OnApiKeyRepositoryListener
import com.finerioconnect.sdk.core.services.ApiKeyService
import com.finerioconnect.sdk.models.FCApiKey
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ApiKeyRepositoryImpl : ApiKeyRepository, OnApiKeyRepositoryListener {
    private var mListener: OnApiKeyRepositoryListener? = null

    override fun validateApiKey(apiKey: String) {
        val tokenService = ApiKeyService

        if (apiKey.isNotEmpty()) {
            val apiKeyModel = FCApiKey(apiKey)
            tokenService.setListener(this)

            GlobalScope.launch {
                tokenService.validate(apiKeyModel)
            }
        } else {
            mListener?.failApiKey("Api Key is missing!")
        }
    }

    override fun successApiKey() {
        Log.i("SDK Core", "Api key success")
    }

    override fun failApiKey(message: String) {
        throw Exception(message)
    }
}