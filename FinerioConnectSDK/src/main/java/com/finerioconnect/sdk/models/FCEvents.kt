package com.finerioconnect.sdk.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FCEvents(
    @Json(name = "deviceId")
    val deviceId: String,
    @Json(name = "event")
    val event: String
)