package com.adrianbukros.github.example

import kotlinx.io.core.ByteReadPacket
import kotlinx.io.core.buildPacket

private const val BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private const val BASE64_MASK: Byte = 0x3f
private const val BASE64_PAD = '='

fun String.toBase64(): String = buildPacket {
    writeStringUtf8(this@toBase64)
}.toBase64()

fun ByteReadPacket.toBase64(): String = buildString {
    val data = ByteArray(3)
    while (remaining > 0) {
        val read = readAvailable(data)
        data.clearFrom(read)

        val padSize = (data.size - read) * 8 / 6
        val chunk = (data[0].toInt() shl 16) or (data[1].toInt() shl 8) or data[2].toInt()

        for (index in data.size downTo padSize) {
            val char = (chunk shr (6 * index)) and BASE64_MASK.toInt()
            append(char.toBase64())
        }

        repeat(padSize) { append(BASE64_PAD) }
    }
}

internal fun ByteArray.clearFrom(from: Int) {
    (from until size).forEach { this[it] = 0 }
}

internal fun Int.toBase64(): Char = BASE64_ALPHABET[this]