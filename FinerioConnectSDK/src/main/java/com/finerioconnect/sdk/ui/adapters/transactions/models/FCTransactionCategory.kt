package com.finerioconnect.sdk.ui.adapters.transactions.models

import com.finerioconnect.sdk.models.FCImage

data class FCTransactionCategory(
    val name: String = "",
    val image: FCImage? = null,
    val backgroundColor: String? = null,
    val textColor: String? = null,
)
