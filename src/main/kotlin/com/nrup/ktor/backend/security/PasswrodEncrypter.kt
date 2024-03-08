package com.nrup.ktor.backend.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private const val SECRET_KEY = "987654321"
private const val ALGORITHM = "HmacSHA1"
private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password: String): String {
    val hMac = Mac.getInstance(ALGORITHM)
    hMac.init(HMAC_KEY)
    return hex(hMac.doFinal(password.toByteArray(Charsets.UTF_8)))
}
