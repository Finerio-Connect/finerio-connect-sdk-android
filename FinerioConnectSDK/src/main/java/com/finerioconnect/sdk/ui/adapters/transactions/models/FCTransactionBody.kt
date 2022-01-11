package com.finerioconnect.sdk.ui.adapters.transactions.models

import com.finerioconnect.sdk.ui.adapters.transactions.FCTransactionAdapter

data class FCTransactionBody<T>(
    var id: String = "",
    var image: String = "",
    var tintImage: Int? = null,
    var name: String = "",
    var category: FCTransactionCategory? = null,
    var amount: Double? = null,
    var duplicated: Boolean = false,
    var configAmountColor: Boolean = true,
    var data: T? = null
) : FCTransactionRow() {
    override fun viewType(): Int = FCTransactionAdapter.ITEM_VIEW_TYPE_ITEM
}