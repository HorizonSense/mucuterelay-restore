package com.mucheng.mucute.relay.util

import java.time.Instant

data class XboxIdentityToken(
    val token: String,
    val notAfter: Long
) {
    val expired: Boolean
        get() = notAfter < Instant.now().epochSecond
}