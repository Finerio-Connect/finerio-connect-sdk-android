package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class FCAnalysisCategory(
    val category: FCCategory,
    val amount: BigDecimal,
    val subCategories: List<FCAnalysisSubCategory>
) : Parcelable