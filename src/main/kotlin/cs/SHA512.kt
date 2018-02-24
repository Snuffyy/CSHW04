package cs

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin
import java.security.MessageDigest

fun toSHA512(text: String) : String{
    val encryptor = MessageDigest.getInstance("SHA-512")
    val textBytes = text.toByteArray(Charsets.UTF_8)
    val hashedBytes = encryptor.digest(textBytes)

    return HexBin.encode(hashedBytes)
}