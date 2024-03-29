package ok.profile.app.ktor.routing

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import ok.profile.business.logic.IProfileService
import ok.profile.common.be.context.Context
import ok.profile.transport.main.mp.request.*
import ok.profile.transport.main.mp.response.*
import ok.profile.transport.mappers.*

fun Route.profile(profileService: IProfileService) {

    route("/profile") {
        post("/read") {
            respond(badResponse = MpReadResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                val request = call.receive<MpReadRequest>()
                val context = Context().apply {
                    setRequest(request)
                }
                profileService.read(context)
                context.buildReadResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
            }
        }

        post("/create") {
            respond(badResponse = MpCreateResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                val request = call.receive<MpCreateRequest>()
                val context = Context().apply {
                    setRequest(request)
                }
                profileService.create(context)
                context.buildCreateResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
            }
        }

        post("/update") {
            respond(badResponse = MpUpdateResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                val request = call.receive<MpUpdateRequest>()
                val context = Context().apply {
                    setRequest(request)
                }
                profileService.update(context)
                context.buildUpdateResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
            }
        }

        post("/delete") {
            respond(badResponse = MpDeleteResponse(status = ResponseStatusDto.BAD_REQUEST, deleted = false)) {
                val request = call.receive<MpDeleteRequest>()
                val context = Context().apply {
                    setRequest(request)
                }
                profileService.delete(context)
                context.buildDeleteResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
            }
        }
        post("/filter") {
            respond(badResponse = MpListResponse(status = ResponseStatusDto.BAD_REQUEST)) {
                val request = call.receive<MpListRequest>()
                val context = Context().apply {
                    setRequest(request)
                }
                profileService.filter(context)
                context.buildListResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
            }
        }
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