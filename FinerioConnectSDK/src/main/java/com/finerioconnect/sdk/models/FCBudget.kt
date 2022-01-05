package com.finerioconnect.sdk.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class FCBudget(
    var id: String?,
    var name: String,
    var categoryId: String?,
    var categoryColor: String?,
    var amount: BigDecimal?,
    var spent: BigDecimal?,
    var leftToSpend: BigDecimal?,
    var warningPercentage: BigDecimal?,
    var spentPercentage: BigDecimal?,
    var subBudgets: List<FCBudget>?,
    var image: FCImage? = null
) : Parcelable
