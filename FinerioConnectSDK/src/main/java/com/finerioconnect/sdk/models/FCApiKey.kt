package com.finerioconnect.sdk.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FCApiKey(
    @Json(name = "apiKey")
    val apiKey: String
)
