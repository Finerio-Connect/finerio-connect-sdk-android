package com.finerioconnect.sdk.shared.enums

enum class FCCredentialStatus {
    ACTIVE,
    INACTIVE,
    VALIDATE,
    INVALID,
    PARTIALLY_ACTIVE;

    companion object {

        fun getCredentialStatus(credentialStatus: String): FCCredentialStatus{
            for(i in values().indices){
                if(values()[i].toString() == credentialStatus){
                    return values()[i]
                }
            }
            return ACTIVE
        }

    }
}