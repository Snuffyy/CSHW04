package cs

class Utils {

    companion object {
        val ASCII_ENCODE: Map<Int, Char> = getASCIIencode()
        val ASCII_DECODE: Map<Char, Int> = getASCIIdecode()
        val ASCII_LEN: Int = ASCII_ENCODE.size
    }
}

fun getASCIIencode(): Map<Int, Char> {
    val map = mutableMapOf<Int, Char>()

    for (ascii in 0..95) {
        map[ascii] = (ascii + 32).toChar()
    }

    return map
}

fun getASCIIdecode(): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()

    for (ascii in 0..95) {
        map[(ascii + 32).toChar()] = ascii
    }

    return map
}