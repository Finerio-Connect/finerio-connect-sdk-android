package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FCImage(
    var name : String,
    var tintColor: Int?= null
) : Parcelable