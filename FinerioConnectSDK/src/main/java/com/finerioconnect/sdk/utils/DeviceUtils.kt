package com.finerioconnect.sdk.utils

import android.content.Context
import com.finerioconnect.sdk.shared.Constants
import java.util.*

object DeviceUtils {
    fun getUUID(context: Context): String {
        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        var uuid = preferences.getString(Constants.Preferences.UUID, "")

        if (uuid.isNullOrEmpty()) {
            val editor = preferences.edit()
            uuid = UUID.randomUUID().toString()
            editor.putString(Constants.Preferences.UUID, uuid)
            editor.apply()
        }

        return uuid
    }
}