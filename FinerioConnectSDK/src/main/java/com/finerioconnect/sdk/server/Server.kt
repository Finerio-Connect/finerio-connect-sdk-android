package com.finerioconnect.sdk.server

interface Server {

    fun<T: FinerioConnectApi> api(apiClient: Class<T>): T

}