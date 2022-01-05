package com.finerioconnect.sdk.core.events

import android.util.Log
import com.finerioconnect.sdk.core.events.interfaces.EventsRepository
import com.finerioconnect.sdk.core.events.interfaces.OnEventsRepositoryListener
import com.finerioconnect.sdk.core.services.EventsService
import com.finerioconnect.sdk.models.FCEvents
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventsRepositoryImpl : EventsRepository, OnEventsRepositoryListener {
    override fun sendEvent(eventName: String, uuid: String) {
        val fcEvents = FCEvents(uuid, eventName)

        EventsService.setListener(this)
        GlobalScope.launch {
            EventsService.sendEvent(fcEvents)
        }
    }

    override fun successEvent() {
        Log.i("Events Service", "Event sent!")
    }

    override fun failEvent(message: String) {
        throw Exception(message)
    }
}