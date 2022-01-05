package com.finerioconnect.sdk.ui.adapters.transactions.models

import com.finerioconnect.sdk.ui.adapters.transactions.FCTransactionAdapter

data class FCTransactionBody<T>(
    val image: String = "",
    val tintImage: Int? = null,
    val name: String = "",
    val category: FCTransactionCategory? = null,
    val amount: Double? = null,
    val duplicated: Boolean = false,
    val configAmountColor: Boolean = true,
    val data: T? = null
) : FCTransactionRow() {
    override fun viewType(): Int = FCTransactionAdapter.ITEM_VIEW_TYPE_ITEM
}