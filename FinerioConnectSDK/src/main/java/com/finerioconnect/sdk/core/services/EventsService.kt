package com.finerioconnect.sdk.core.services

import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.core.events.interfaces.OnEventsRepositoryListener
import com.finerioconnect.sdk.models.FCEvents
import com.finerioconnect.sdk.server.FinerioConnectApi
import com.finerioconnect.sdk.server.ServerImpl

object EventsService {
    private var mListener: OnEventsRepositoryListener? = null

    suspend fun sendEvent(fcEvents: FCEvents) {
        val apiKey = FinerioConnectSDK.shared.apiKey
        val serverImpl = ServerImpl(FinerioConnectApi.BASE_URL, false)
        val api = serverImpl.api(FinerioConnectApi::class.java)
        val call = api.sendEvent(apiKey, fcEvents)

        if (call != null) {
            when (call.code()) {
                200 -> mListener?.successEvent()
                401 -> mListener?.failEvent("Invalid client! Contact Finerio")
                400 -> mListener?.failEvent("Client not exist! Please check your ApiKey")
                else -> {
                    mListener?.failEvent("Server error response")
                }
            }
        } else {
            mListener?.failEvent("Server error response")
        }
    }

    fun setListener(listener: OnEventsRepositoryListener?) {
        this.mListener = listener
    }
}