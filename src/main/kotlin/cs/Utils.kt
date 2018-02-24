package cs

class Utils {

    companion object {
        val ASCII_ENCODE_TABLE: Map<Int, Char> = getASCIIencode()
        val ASCII_DECODE_TABLE: Map<Char, Int> = getASCIIdecode()
        val ASCII_TABLE_LEN: Int = ASCII_ENCODE_TABLE.size
    }
}

fun getASCIIencode(): Map<Int, Char> {
    val map = mutableMapOf<Int, Char>()

    for (ascii in 0..94) {
        map[ascii] = (ascii + 32).toChar()
    }

    return map
}

fun getASCIIdecode(): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()

    for (ascii in 0..94) {
        map[(ascii + 32).toChar()] = ascii
    }

    return map
}

fun isOfSameLen(msg: String, key: String): Boolean = msg.length == key.length