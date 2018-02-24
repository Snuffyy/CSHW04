package cs

import java.security.MessageDigest

fun toSHA512(text: String): String {
    val encryptor = MessageDigest.getInstance("SHA-512")
    val textBytes = text.toByteArray(Charsets.UTF_8)
    val hashedBytes = encryptor.digest(textBytes)

    return toHex(hashedBytes)
}

fun toHex(bytes: ByteArray): String {
    val sb = StringBuilder()

    for (index in 0 until bytes.size) {
        sb.append(String.format("%02X", bytes[index]))
    }

    return sb.toString()
}