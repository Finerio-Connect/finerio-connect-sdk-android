package com.finerioconnect.sdk.ui.adapters.transactions.models

import com.finerioconnect.sdk.ui.adapters.transactions.FCTransactionAdapter

data class FCTransactionHeader(
    val dateTitle: String = "",
    val amount: Double? = null,
) : FCTransactionRow() {
    override fun viewType(): Int = FCTransactionAdapter.ITEM_VIEW_TYPE_HEADER
}
