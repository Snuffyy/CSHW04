package cs

/**
 * Utils class with handy functions.
 */
class Utils {

    companion object {
        val ASCII_ENCODE_TABLE: Map<Int, Char> = getASCIIencode()
        val ASCII_DECODE_TABLE: Map<Char, Int> = getASCIIdecode()
        val ASCII_TABLE_LEN: Int = ASCII_ENCODE_TABLE.size
    }
}

/**
 * Generate encode table.
 */
fun getASCIIencode(): Map<Int, Char> {
    val map = mutableMapOf<Int, Char>()

    for (ascii in 0..94) {
        map[ascii] = (ascii + 32).toChar()
    }

    return map
}

/**
 * Generate decode table.
 */
fun getASCIIdecode(): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()

    for (ascii in 0..94) {
        map[(ascii + 32).toChar()] = ascii
    }

    return map
}

/**
 * Check if 2 strings are of the same length.
 */
fun isOfSameLen(msg: String, key: String): Boolean = msg.length == key.length