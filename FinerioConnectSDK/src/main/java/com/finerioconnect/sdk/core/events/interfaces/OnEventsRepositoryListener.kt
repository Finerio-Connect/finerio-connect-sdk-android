package com.finerioconnect.sdk.core.events.interfaces

interface OnEventsRepositoryListener {
    fun successEvent()
    fun failEvent(message: String)
}