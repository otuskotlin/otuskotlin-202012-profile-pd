package ok.profile.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.json.Json
import ok.profile.app.ktor.service.ProfileService
import ok.profile.common.be.context.BeContext
import ok.profile.transport.main.mp.request.*
import ok.profile.transport.main.mp.response.*
import ok.profile.transport.mappers.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    val profileService = ProfileService()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/profile") {

            post("/get") {
                respond(badResponse = MpReadResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                    val request = call.receive<MpReadRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.get(context)
                    context.buildReadResponse().copy(
                        onRequest = request.requestId,
                        responseId = "resp-id",
                    )
                }
            }

            post("/create") {
                respond(badResponse = MpCreateResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                    val request = call.receive<MpCreateRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.create(context)
                    context.buildCreateResponse().copy(
                        onRequest = request.requestId,
                        responseId = "resp-id"
                    )
                }
            }

            post("/update") {
                respond(badResponse = MpUpdateResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                    val request = call.receive<MpUpdateRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.update(context)
                    context.buildUpdateResponse().copy(
                        onRequest = request.requestId,
                        responseId = "resp-id"
                    )
                }
            }

            post("/delete") {
                respond(badResponse = MpDeleteResponse(status = ResponseStatusDto.BAD_REQUEST, deleted = false)) {
                    val request = call.receive<MpDeleteRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.delete(context)
                    context.buildDeleteResponse().copy(
                        onRequest = request.requestId,
                        responseId = "resp-id"
                    )
                }
            }
            post("/filter") {
                respond(badResponse = MpListResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                    val request = call.receive<MpListRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.filter(context)
                    context.buildListResponse()
                }
            }
        }

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

suspend inline fun <reified T : IMpResponse> PipelineContext<Unit, ApplicationCall>.respond(
    badResponse: T,
    createResponse: () -> T,
) {
    try {
        val response = createResponse()
        call.respond(response)
    } catch (e: Throwable) {
        call.respond(badResponse)
    }
}