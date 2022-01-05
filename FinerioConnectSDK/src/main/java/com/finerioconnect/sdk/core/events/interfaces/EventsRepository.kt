package com.finerioconnect.sdk.core.events.interfaces

interface EventsRepository {
    fun sendEvent(eventName: String, uuid: String)
}