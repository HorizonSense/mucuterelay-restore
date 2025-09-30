package com.mucheng.mucute.relay.address

import java.net.InetSocketAddress

data class MuCuteAddress(
    val hostName: String,
    val port: Int
)
val MuCuteAddress.inetSocketAddress: InetSocketAddress
    get() = InetSocketAddress(this.hostName, this.port)

fun InetSocketAddress.toMuCuteAddress(): MuCuteAddress =
    MuCuteAddress(this.hostName, this.port)