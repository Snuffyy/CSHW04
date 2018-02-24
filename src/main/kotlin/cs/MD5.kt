package cs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


fun decrypt(string: String): String {

    val url = URL("http://www.nitrxgen.net/md5db/$string")

    with(url.openConnection() as HttpURLConnection) {

        BufferedReader(InputStreamReader(inputStream)).use {
            return it.readLine()
        }
    }
}