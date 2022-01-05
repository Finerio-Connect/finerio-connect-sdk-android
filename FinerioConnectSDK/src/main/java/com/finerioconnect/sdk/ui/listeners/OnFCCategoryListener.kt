package com.finerioconnect.sdk.ui.listeners

import com.finerioconnect.sdk.models.FCCategory

interface OnFCCategoryListener {
    fun didSelectedCategory(category: FCCategory?)
}