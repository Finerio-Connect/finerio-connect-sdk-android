package com.finerioconnect.sdk.ui.listeners

import com.finerioconnect.sdk.models.FCCategory

interface OnFCSubcategoryListener {
    fun didSelectedSubcategory(subcategory: FCCategory?)
}