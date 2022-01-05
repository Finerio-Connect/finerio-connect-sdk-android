package com.finerioconnect.sdk.extensions

import java.text.DecimalFormat

fun Double.formatAmount(digits: Int = 2): String {
    val decFormat = DecimalFormat("$#,###.##")
    decFormat.minimumFractionDigits = digits
    decFormat.maximumFractionDigits = digits
    return decFormat.format(this)
}