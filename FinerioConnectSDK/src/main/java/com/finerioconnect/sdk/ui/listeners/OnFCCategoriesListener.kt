package com.finerioconnect.sdk.ui.listeners

import com.finerioconnect.sdk.models.FCCategory

interface OnFCCategoriesListener {
    fun didSelectedCategories(category: ArrayList<FCCategory>?)
}