package ok.profile.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import ok.profile.app.ktor.controllers.kafkaEndpoints
import ok.profile.app.ktor.routing.profile
import ok.profile.business.logic.ProfileService
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.Producer

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(
    testing: Boolean = false,
    kafkaTestConsumer: Consumer<String, String>? = null,
    kafkaTestProducer: Producer<String, String>? = null,
) {

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

    val brokers = environment.config.propertyOrNull("profile.kafka.brokers")?.getString()
    if (brokers != null) {
        kafkaEndpoints(
            brokers = brokers,
            kafkaConsumer = kafkaTestConsumer,
            kafkaProducer = kafkaTestProducer,
            profileService = profileService,
        )
    }
}