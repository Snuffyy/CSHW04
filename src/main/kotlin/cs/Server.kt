package cs

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.io.File

fun Application.main() {
    install(DefaultHeaders)
    install(CORS)
    install(Routing) {
        get("/sha") {
            val msg = call.request.queryParameters["m"] ?: ""
            call.respondText(toSHA512(msg), ContentType.Text.Plain)
        }
        get("/md5") {
            val hash = call.request.queryParameters["h"] ?: ""
            call.respondText(decrypt(hash), ContentType.Text.Plain)
        }
        get("/otp/d") {
            val msg = call.request.queryParameters["m"] ?: ""
            val key = call.request.queryParameters["k"] ?: ""
            call.respondText(decrypt(msg, key), ContentType.Text.Plain)
        }
        get("/otp/e") {
            val msg = call.request.queryParameters["m"] ?: ""
            val key = call.request.queryParameters["k"] ?: ""
            call.respondText(encrypt(msg, key), ContentType.Text.Plain)
        }
        get("/") {
            call.respondFile(File("src/main/resources/index.html"))
        }
        get("/materialize.min.css") {
            call.respondFile(File("src/main/resources/materialize.min.css"))
        }
        get("/styles.css") {
            call.respondFile(File("src/main/resources/styles.css"))
        }
        get("/main.js") {
            call.respondFile(File("src/main/resources/main.js"))
        }
    }
}
