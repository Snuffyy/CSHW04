package cs

import cs.Utils.Companion.ASCII_DECODE_TABLE
import cs.Utils.Companion.ASCII_ENCODE_TABLE
import cs.Utils.Companion.ASCII_TABLE_LEN

/**
 * Encrypt a message.
 *
 * CONSTRAINTS: can only produce right results for ASCII values in range [32; 127)
 *
 * @param msg to encrypt
 * @param key to encrypt with
 *
 * @return encrypted message
 */
fun encrypt(msg: String, key: String): String {

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

/**
 * Decrypt a message.
 *
 * CONSTRAINTS: can only produce right results for ASCII values in range [32; 127)
 *
 * @param encrypted message
 * @param key to decrypt with
 *
 * @return decrypted message
 */
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

/**
 * Encrypt a char.
 *
 * @param current char
 * @param key for current char
 *
 * @return encrypted char
 */
fun getEncryptedChar(current: Char, key: Char): Char? {
    val swappedCurrent = ASCII_DECODE_TABLE[current] ?: 0 // get table value for current char
    val swappedKey = ASCII_DECODE_TABLE[key] ?: 0 // get table value for current key

    // tableValueForEncryptedChar = (tableValueForCurrentChar + tableValueForCurrentKey) % lengthOfTheTable
    return ASCII_ENCODE_TABLE[(swappedCurrent + swappedKey) % ASCII_TABLE_LEN]
}

/**
 * Decrypt a char.
 *
 * @param current char
 * @param key for current char
 *
 * @return decrypted char
 */
fun getDecryptedChar(current: Char, key: Char): Char? {
    val decodedKey = ASCII_DECODE_TABLE[key] ?: 0
    val decodedCurrent = ASCII_DECODE_TABLE[current] ?: 0

    // tableValueForDecryptedChar = lengthOfTheTable - |(tableValueForCurrentChar - tableValueForCurrentKey)| % lengthOfTheTable
    return ASCII_ENCODE_TABLE[(ASCII_TABLE_LEN - Math.abs(decodedCurrent - decodedKey)) % ASCII_TABLE_LEN]
}