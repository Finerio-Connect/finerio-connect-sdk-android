package com.finerioconnect.sdk.ui.models

/**
 * Palette
 * Example: new Palette(ContextCompat.getColor(this, R.color.budget_green), Color.parseColor("#ffa02e36"));
 */
data class SwitchButtonPalette(
    var strokeColor: Int? = null,
    var bgColor: Int? = null,
    var bgColorActivated: Int? = null,
    var titleColor: Int? = null,
    var titleColorActivated: Int? = null
)