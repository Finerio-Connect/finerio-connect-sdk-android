package com.finerioconnect.sdk.extensions

import com.finerioconnect.sdk.shared.Constants.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun Date.month(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar[Calendar.MONTH]
}

fun Date?.monthFormatter(): String = this?.let {
    SimpleDateFormat(DateFormat.SHORT_MONTH_FORMAT, Locale(DateFormat.LOCALE_LANGUAGE)).format(it)
} ?: ""


//TEMP EXTENSIONS
fun Date?.formatDate(format: String = DateFormat.DATE_FORMAT): String = this?.let {
    SimpleDateFormat(format, Locale(DateFormat.LOCALE_LANGUAGE)).format(it)
} ?: ""

fun Date.getLocaleShortMonth(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return DateFormatSymbols().shortMonths[calendar[Calendar.MONTH]]
}

fun Date.dateTitle(): String = this.getLocaleMonth() + " " + this.year()

fun Date.getLocaleMonth(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return DateFormatSymbols().months[calendar[Calendar.MONTH]]
}

fun Date.year(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar[Calendar.YEAR]
}

fun Date.dropTimeFromDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}