package com.finerioconnect.sdk.models

import java.util.*

data class FCResume(
    var id: String = "",
    var month: Int = 0,
    var date: Date = Date(),
    var total: Double = 0.0,
    var category: String = "",
    var color: String = "",
    var transactions: List<FCResumeTransaction>
)
