package com.finerioconnect.sdk.utils

import android.graphics.drawable.GradientDrawable

object Utils {

    fun roundedBackground(radius: Float, color: Int): GradientDrawable =
        GradientDrawable().apply {
            cornerRadius = radius
            setColor(color)
        }

}