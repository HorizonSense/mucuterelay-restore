package com.mucheng.mucute.relay.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.time.Instant

class XboxIdentityTokenCacheFileSystem(
    val cacheFile: File,
    override val identifier: String
) : IXboxIdentityTokenCache {

    override fun cache(device: XboxDeviceInfo, token: XboxIdentityToken) {
        val json = if (!cacheFile.exists()) {
            JsonObject()
        } else {
            try {
                JsonParser.parseReader(InputStreamReader(FileInputStream(cacheFile), Charsets.UTF_8)).asJsonObject
            } catch (e: Throwable) {
                println("Load config: $e")
                JsonObject()
            }
        }
        val identifierJson = if (json.has(identifier)) {
            val el = json.get(identifier)
            if (el.isJsonObject) el.asJsonObject else JsonObject()
        } else {
            JsonObject()
        }
        val tokenJson = JsonObject()
        tokenJson.addProperty("token", token.token)
        tokenJson.addProperty("expires", token.notAfter)
        identifierJson.add(device.deviceType, tokenJson)
        json.add(identifier, identifierJson)
        removeExpired(json)
        cacheFile.writeText(AuthUtils.gson.toJson(json), Charsets.UTF_8)
    }

    override fun checkCache(device: XboxDeviceInfo): XboxIdentityToken? {
        if (!cacheFile.exists()) return null
        val json = try {
            JsonParser.parseReader(InputStreamReader(FileInputStream(cacheFile), Charsets.UTF_8)).asJsonObject
        } catch (e: Throwable) {
            println("Load config: $e")
            return null
        }
        try {
            if (json.has(identifier)) {
                val identifierJson = json.get(identifier).asJsonObject
                if (identifierJson.has(device.deviceType)) {
                    val deviceJson = identifierJson.get(device.deviceType).asJsonObject
                    if (deviceJson.get("expires").asLong >= Instant.now().epochSecond && deviceJson.has("token")) {
                        return XboxIdentityToken(deviceJson.get("token").asString, deviceJson.get("expires").asLong)
                    } else {
                        identifierJson.remove(device.deviceType)
                        removeExpired(json)
                        cacheFile.writeText(AuthUtils.gson.toJson(json), Charsets.UTF_8)
                        return null
                    }
                }
            }
        } catch (e: Throwable) {
            println("Check cache: $e")
        }
        return null
    }

    private fun removeExpired(json: JsonObject) {
        val epoch = Instant.now().epochSecond
        for ((_, value) in json.entrySet()) {
            if (value.isJsonObject) {
                val identifierJson = value.asJsonObject
                val toRemove = mutableListOf<String>()
                for ((key, value2) in identifierJson.entrySet()) {
                    if (value2.isJsonObject) {
                        val deviceJson = value2.asJsonObject
                        if (deviceJson.get("expires").asLong < epoch) {
                            toRemove.add(key)
                        }
                    }
                }
                for (it in toRemove) {
                    identifierJson.remove(it)
                }
            }
        }
    }
}