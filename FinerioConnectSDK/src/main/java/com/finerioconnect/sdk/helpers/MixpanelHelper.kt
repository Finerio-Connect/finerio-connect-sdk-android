package com.finerioconnect.sdk.helpers

import android.content.Context
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
        val jsonObject = JSONObject(superProperties)
        mMixpanelAPI.registerSuperProperties(jsonObject)
    }

    fun sendEvent(eventName: String, properties: Map<String, Any>) {
        val jsonObject = JSONObject(properties)
        mMixpanelAPI.track(eventName, jsonObject)
    }

    fun sendEvent(eventName: String) {
        mMixpanelAPI.track(eventName)
    }
}