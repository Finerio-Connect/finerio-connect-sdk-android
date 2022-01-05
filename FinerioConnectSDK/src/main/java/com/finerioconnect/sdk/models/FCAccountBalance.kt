package com.finerioconnect.sdk.models

data class FCAccountBalance(
    val debitAmount: Double,
    val creditAmount: Double,
    val shortTermBalanceAmount: Double,
    val investmentAmount: Double,
    val creditsAmount: Double,
    val longTermBalanceAmount: Double,
    val balanceAmountLabel: Double,
    val credentials: List<FCCredential>
)