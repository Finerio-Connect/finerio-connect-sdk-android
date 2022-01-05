package com.finerioconnect.sdk.core.apikey.interfaces

interface OnApiKeyRepositoryListener {
    fun successApiKey()
    fun failApiKey(message: String)
}