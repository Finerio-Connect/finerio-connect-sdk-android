package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FCAccount(
    var id: String?,
    var name: String?,
    var type: String?,
    var balance: Double? = null,
    var bank: FCBank? = null
) : Parcelable