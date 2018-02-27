package cs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Use 'nitrxgen.net' service API to decode MD5 hash.
 *
 * @param hash to decode
 * @return possibly decoded hash or error message
 */
fun decode(hash: String): String {

    val url = URL("http://www.nitrxgen.net/md5db/$hash")

    // begin connection with a server
    with(url.openConnection() as HttpURLConnection) {

        BufferedReader(InputStreamReader(inputStream)).use {
            // return first line from a response or error message
            return it.readLine() ?: "[Could not decode. Try again or enter another hash.]"
        }
    }
}