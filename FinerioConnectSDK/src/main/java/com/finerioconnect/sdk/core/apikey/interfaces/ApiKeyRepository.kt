package com.finerioconnect.sdk.core.apikey.interfaces

interface ApiKeyRepository {
    fun validateApiKey(apiKey: String)
    fun setListener(listener: OnApiKeyRepositoryListener?)
}