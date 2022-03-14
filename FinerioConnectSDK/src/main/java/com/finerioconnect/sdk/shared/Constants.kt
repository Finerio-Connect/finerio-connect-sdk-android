package com.finerioconnect.sdk.shared

object Constants {

    object Commons {
        const val DRAWABLE_NAME = "drawable"
    }

    object URLS {
        const val PRIVACY_TERMS = "https://finerioconnect.com/privacy"
        const val TERMS_AND_CONDITIONS = "https://finerioconnect.com/license"
        const val cdnWidget = "https://cdn.finerio.mx/widget"
    }

    /// Tags
    object Tags {
        const val LOADER = 200
        const val FIELD_SECURITY_CODE = 33
    }

    object ErrorCodes {
        const val UNKNOWN = -1
    }

    object CredentialStatus {
        const val SUCCESS = "SUCCESS"
        const val CREATED = "CREATED"
        const val ACTIVE = "ACTIVE"
        const val FAILURE = "FAILURE"
        const val INTERACTIVE = "INTERACTIVE"
        const val ACCOUNT_CREATED = "ACCOUNT_CREATED"
        const val TRANSACTIONS_CREATED = "TRANSACTIONS_CREATED"
    }

    object DateFormat {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
        const val YEAR_MONTH_DATE_FORMAT = "yyyy-MM"
        const val DATE_FORMAT_WITH_SLASH = "dd/MM/yy"
        const val SHORT_MONTH_FORMAT = "MMM."
        const val LOCALE_LANGUAGE = "es_MX"
    }

    object Mixpanel {
        const val DEVICE_ID = "device_id"
        const val API_KEY = "api_key"
        const val ENABLED = false
    }

    object Preferences {
        const val UUID = "uuid"
        const val VALID_API_KEY = "validApiKey"
    }
}