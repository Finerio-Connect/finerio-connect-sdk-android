package com.finerioconnect.sdk.shared.enums

enum class TransactionType {
    INCOME,
    EXPENSE,
    BALANCE;

    companion object {
        fun getTransactionSelected(transaction: Int): TransactionType =
            if (transaction > values().size) INCOME else values()[transaction]
    }

}