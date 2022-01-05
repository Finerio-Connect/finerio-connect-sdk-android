package com.finerioconnect.sdk.shared.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FCCategoryType : Parcelable {
    SHOW_ALL_ONLY_ONE_CATEGORY,
    SHOW_ALL_MULTI_SELECTION,
    SHOW_ALL_ONLY_ONE_SUBCATEGORY,
    SHOW_ONLY_CATEGORIES,
}