package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class FCAnalysisSubCategory(
    val category: FCCategory,
    val average: BigDecimal,
    val quantity: Int,
    val amount: BigDecimal,
    val transactions: List<FCAnalysisTransaction>
) : Parcelable
