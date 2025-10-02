package com.mucheng.mucute.relay.util

interface IXboxIdentityTokenCache {
    val identifier: String
    fun cache(device: XboxDeviceInfo, token: XboxIdentityToken)
    fun checkCache(device: XboxDeviceInfo): XboxIdentityToken?
}
