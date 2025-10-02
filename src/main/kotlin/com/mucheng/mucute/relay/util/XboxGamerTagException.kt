package com.mucheng.mucute.relay.util

class XboxGamerTagException(val sisuStartUrl: String) : IllegalStateException(
    "Have you registered a Xbox GamerTag? You can register it here: $sisuStartUrl"
)
