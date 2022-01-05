package com.finerioconnect.sdk.shared.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FCBankStatus : Parcelable {
    ACTIVE,
    INACTIVE,
    PARTIALLY_ACTIVE;

    companion object {

        fun getBankStatus(bankStatus: String): FCBankStatus {
            for (i in values().indices) {
                if (values()[i].toString() == bankStatus) {
                    return values()[i]
                }
            }
            return ACTIVE
        }

    }
}