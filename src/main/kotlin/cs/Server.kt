package cs

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("You are not supposed to be here...", ContentType.Text.Plain)
        }
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
    }
}
