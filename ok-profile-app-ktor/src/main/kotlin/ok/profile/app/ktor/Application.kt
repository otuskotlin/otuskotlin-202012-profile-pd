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
import ok.profile.app.ktor.service.InMemoryProfileService
import ok.profile.app.ktor.service.ProfileService
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    if (!testing) {
        di {
            bind<ProfileService>() with singleton { InMemoryProfileService() }
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        profile()
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