package com.finerioconnect.sdk.ui.base

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.finerioconnect.sdk.core.FinerioConnectSDK
import com.finerioconnect.sdk.helpers.logWarn

open class CategoryBaseView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    init {
        orientation = VERTICAL
    }

    protected fun checkSDKCoreConfiguration() {
        if (!FinerioConnectSDK.shared.isReadySDK) {
            logWarn("You must configure the FineroConnectSDK to continue")
            return
        }
    }
}