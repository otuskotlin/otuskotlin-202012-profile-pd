package ok.profile.app.ktor

import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import ok.profile.app.ktor.service.ProfileService
import ok.profile.common.be.context.BeContext
import ok.profile.transport.main.mp.request.MpReadRequest
import ok.profile.transport.main.mp.response.MpCreateResponse
import ok.profile.transport.main.mp.response.ResponseStatusDto
import ok.profile.transport.mappers.setRequest
import ok.profile.transport.mappers.createReadResponse

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    val profileService = ProfileService()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/profile") {
            post("/get") {
                try {
                    val request = call.receive<MpReadRequest>()
                    val context = BeContext().apply {
                        setRequest(request)
                    }
                    profileService.get(context)
                    val response = context.createReadResponse().copy(
                        onRequest = request.requestId,
                        responseId = "resp-id",
                    )
                    call.respond(response)
                } catch (e: Throwable) {
                    call.respond(MpCreateResponse(status = ResponseStatusDto.BAD_REQUEST))
                }
            }
        }

        /*
            post("/create") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestDemandCreate
                    call.respond(demandService.create(query))
                } catch(e: Throwable) {
                    call.respond(
                        MpResponseDemandCreate(
                            status = ResponseStatusDto.BAD_REQUEST
                        )
                    )
                }
            }
            post("/update") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestDemandUpdate
                    call.respond(demandService.update(query))
                } catch(e: Throwable) {
                    call.respond(
                        MpResponseDemandUpdate(
                            status = ResponseStatusDto.BAD_REQUEST
                        )
                    )
                }
            }
            post("/delete") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestDemandDelete
                    call.respond(demandService.delete(query))
                } catch(e: Throwable) {
                    call.respond(
                        MpResponseDemandDelete(
                            status = ResponseStatusDto.BAD_REQUEST
                        )
                    )
                }
            }
            post("/filter") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestDemandList
                    call.respond(demandService.filter(query))
                } catch(e: Throwable) {
                    call.respond(
                        MpResponseDemandList(
                            status = ResponseStatusDto.BAD_REQUEST
                        )
                    )
                }
            }
         */

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