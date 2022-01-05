package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FCTransaction(
    var id: String = "",
    var date: Date?= null,
    var description: String = "",
    var cleanDescription: String? = null,
    var amount: Double = 0.0,
    var duplicated: Boolean? = null,
    var accountId: String = "",
    var inBalance: Boolean? = null,
    var bank: FCBank? = null,
    var category: FCCategory? = null
) : Parcelable