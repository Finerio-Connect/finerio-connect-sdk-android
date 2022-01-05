package com.finerioconnect.sdk.core

import com.finerioconnect.sdk.models.Fonts
import com.finerioconnect.sdk.shared.enums.Environment
import com.finerioconnect.sdk.shared.enums.LogLevel

class Configuration {

    // MARK: Global Constants

    companion object {
        const val ERROR_DOMAIN = "com.finerioconnect.network"
        const val FINERIO_CONNECT_ERROR_KEY = "FinerioConnectErrorKey"
        const val FINERIO_CONNECT_ERROR_DEBUG_KEY = "FinerioConnectErrorDebugKey"
    }

    // MARK: Global Variables

    var logLevel: LogLevel = LogLevel.INFO
        private set
    var environment: Environment = Environment.SANDBOX
        private set
    var fonts = Fonts()
        private set
    var isReadySDK = false
        private set
    var apiKey = ""
        private set

    fun setLogLevel(logLevel: LogLevel) = apply {
        this.logLevel = logLevel
    }

    fun setEnvironment(environment: Environment) = apply {
        this.environment = environment
    }

    fun setFonts(fonts: Fonts) = apply {
        this.fonts = fonts
    }

    fun setIsReadySDK(isReadySDK: Boolean) = apply {
        this.isReadySDK = isReadySDK
    }

    fun setUserToken(userToken: String) = apply {
        this.apiKey = userToken
    }
}