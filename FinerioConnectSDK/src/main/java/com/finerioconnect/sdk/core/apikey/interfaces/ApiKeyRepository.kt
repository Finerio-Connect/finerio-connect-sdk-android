package com.finerioconnect.sdk.core.apikey.interfaces

interface ApiKeyRepository {
    fun validateApiKey(apiKey: String)
}