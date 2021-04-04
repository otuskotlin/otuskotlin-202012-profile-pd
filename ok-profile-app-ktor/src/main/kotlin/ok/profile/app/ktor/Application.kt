package ok.profile.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import ok.profile.app.ktor.routing.profile
import ok.profile.business.logic.ProfileService

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    val profileService = ProfileService()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        profile(profileService)
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json,
            json = Json { prettyPrint = true }
        )
    }

}