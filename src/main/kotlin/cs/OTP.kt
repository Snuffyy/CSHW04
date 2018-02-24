package cs

fun isOfSameLen(msg: String, key: String): Boolean = msg.length == key.length


fun encrypt(msg: String, key: String = ""): String {

    if (!isOfSameLen(msg, key)) return ""

    // will use ASCII values as chars representations
    val sb = StringBuilder()

    var toEncrypt: Char
    var keyChar: Char

    for (index in 0 until msg.length) {
        toEncrypt = msg[index]
        keyChar = key[index]

        sb.append(getEncryptedChar(toEncrypt, keyChar))
    }

    return sb.toString()
}


fun decrypt(encrypted: String, key: String): String {

    if (!isOfSameLen(encrypted, key)) return ""

    val sb = StringBuilder()

    var toDecrypt: Char
    var keyChar: Char

    for (index in 0 until encrypted.length) {
        toDecrypt = encrypted[index]
        keyChar = key[index]

        sb.append(getDecryptedChar(toDecrypt, keyChar))
    }

    return sb.toString()
}


fun getEncryptedChar(current: Char, key: Char): Char? {
    val swappedCurrent = Utils.ASCII_DECODE[current] ?: 0
    val swappedKey = Utils.ASCII_DECODE[key] ?: 0

    return Utils.ASCII_ENCODE[(swappedCurrent + swappedKey) % Utils.ASCII_LEN]
}

fun getDecryptedChar(current: Char, key: Char): Char? {
    val decodedKey = Utils.ASCII_DECODE[key] ?: 0
    val decodedCurrent = Utils.ASCII_DECODE[current] ?: 0

    return Utils.ASCII_ENCODE[(Utils.ASCII_LEN - (Math.abs(decodedCurrent - decodedKey))) % Utils.ASCII_LEN]
}