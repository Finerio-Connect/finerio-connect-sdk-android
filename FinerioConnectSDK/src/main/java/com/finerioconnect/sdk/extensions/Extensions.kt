package com.finerioconnect.sdk.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.finerioconnect.sdk.shared.Constants

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun Context.findImageIdentifier(name: String): Int =
    this.resources.getIdentifier(name, Constants.Commons.DRAWABLE_NAME, this.packageName)

fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Context.dpToPx(dp: Float) =
    dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

fun Context.pxToDp(px: Float) =
    px / (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

fun Context.drawable(drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun Context.color(color: Int) = ContextCompat.getColor(this, color)
