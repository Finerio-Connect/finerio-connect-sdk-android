package com.finerioconnect.sdk.helpers

import android.content.Context
import com.finerioconnect.sdk.shared.Constants
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

class MixpanelHelper {
    private lateinit var mMixpanelAPI: MixpanelAPI

    companion object {
        val instance: MixpanelHelper = MixpanelHelper()
    }

    fun init(context: Context, token: String) {
        mMixpanelAPI = MixpanelAPI.getInstance(context, token)
    }

    fun sendSuperProperties(superProperties: Map<String, Any>) {
        if (Constants.Mixpanel.ENABLED) {
            val jsonObject = JSONObject(superProperties)
            mMixpanelAPI.registerSuperProperties(jsonObject)
        }
    }

    fun sendEvent(eventName: String, properties: Map<String, Any>) {
        if (Constants.Mixpanel.ENABLED) {
            val jsonObject = JSONObject(properties)
            mMixpanelAPI.track(eventName, jsonObject)
        }
    }

    fun sendEvent(eventName: String) {
        if (Constants.Mixpanel.ENABLED) {
            mMixpanelAPI.track(eventName)
        }
    }
}