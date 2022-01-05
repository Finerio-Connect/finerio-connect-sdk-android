package com.finerioconnect.sdk.extensions

import android.content.Context
import android.webkit.URLUtil
import com.finerioconnect.sdk.shared.Constants.DateFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.safe(): String = this ?: ""

fun String?.trim(maximumCharacters: Int): String = this?.let {
    return "${this.substring(0, maximumCharacters)}..."
} ?: ""

fun String?.removeFormatAmount(): Double = try {
    this?.let {
        val format = NumberFormat.getCurrencyInstance()
        format.parse(it)?.toDouble() ?: 0.0
    } ?: 0.0
} catch (e: ParseException) {
    0.0
}

fun String?.isUrl(): Boolean = this?.let {
    URLUtil.isValidUrl(this)
} ?: false


//TEMP EXTENSIONS
fun String?.parseDate(format: String? = null): Date? = try {
    this?.let {
        val dateFormat = format ?: DateFormat.DATE_FORMAT
        SimpleDateFormat(dateFormat, Locale(DateFormat.LOCALE_LANGUAGE)).parse(it)
    }
} catch (e: ParseException) {
    null
}

fun String?.parseShortDate(): Date? = try {
    this?.let {
        SimpleDateFormat(
            DateFormat.YEAR_MONTH_DATE_FORMAT,
            Locale(DateFormat.LOCALE_LANGUAGE)
        ).parse(it)
    }
} catch (e: ParseException) {
    null
}

fun String?.defaultText(context: Context, textId: Int): String = this?.let {
    if (it.isEmpty()) context.getString(textId) else it
} ?: ""
