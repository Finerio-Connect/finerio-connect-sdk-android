package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FCCategory(
    val id: String = "",
    val name: String = "",
    val backgroundColor: String = "",
    val textColor: String = "",
    var image: FCImage? = null,
    val parentCategory: FCCategory? = null,
    var subCategories: List<FCCategory>? = arrayListOf(),
    val parentId: String? = null,
) : Parcelable