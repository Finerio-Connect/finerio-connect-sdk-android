package com.finerioconnect.sdk.core

import android.content.Context
import com.finerioconnect.sdk.core.apikey.interfaces.ApiKeyRepository
import com.finerioconnect.sdk.core.apikey.repositories.ApiKeyRepositoryImpl
import com.finerioconnect.sdk.helpers.MixpanelHelper
import com.finerioconnect.sdk.helpers.logInfo
import com.finerioconnect.sdk.models.Fonts
import com.finerioconnect.sdk.shared.Constants
import com.finerioconnect.sdk.shared.enums.Environment
import com.finerioconnect.sdk.shared.enums.LogLevel
import com.finerioconnect.sdk.utils.DeviceUtils
import java.util.*

class FinerioConnectSDK private constructor() {

    companion object {
        val shared = FinerioConnectSDK()
    }

    private var configuration: Configuration = Configuration()
    private var mixpanelInitialized: Boolean = false

    fun init(configuration: Configuration) = apply {
        this.configuration = configuration
    }

    var logLevel: LogLevel
        get() = configuration.logLevel
        set(value) {
            configuration.setLogLevel(value)
        }

    var environment: Environment
        get() = configuration.environment
        set(value) {
            configuration.setEnvironment(value)
        }

    var fonts: Fonts
        get() = configuration.fonts
        set(value) {
            configuration.setFonts(value)
        }

    var isReadySDK: Boolean
        get() = configuration.isReadySDK
        set(value) {
            configuration.setIsReadySDK(value)
        }

    var apiKey: String
        get() = configuration.apiKey
        set(value) {
            configuration.setUserToken(value)
        }

    fun configure() {
        logInfo("FinerioConnectSDK configured...")
        logInfo("SDK Version: 1.0.0")

        configuration.setIsReadySDK(true)
        validateApiKey()
    }

    fun setupMixpanel(context: Context, token: String) {
        if (!mixpanelInitialized) {
            mixpanelInitialized = true
            if (configuration.apiKey.isEmpty()) {
                throw Exception("You need to set your client token to continue, using FinerioConnectSDK class. Check README file")
            }

            val superProperties: MutableMap<String, Any> = LinkedHashMap()
            superProperties[Constants.Mixpanel.DEVICE_ID] = DeviceUtils.getUUID(context)
            superProperties[Constants.Mixpanel.API_KEY] = configuration.apiKey

            MixpanelHelper.instance.init(context, token)
            MixpanelHelper.instance.sendSuperProperties(superProperties)
        }
    }

    private fun validateApiKey() {
        val repository: ApiKeyRepository = ApiKeyRepositoryImpl()
        repository.validateApiKey(shared.apiKey)
    }
}