package com.finerioconnect.sdk.models

data class FCCredentialModel(
    var name: String = "",
    var image: FCImage? = null,
    var accounts: List<FCAccount>? = null,
    var collapsed: Boolean
)
