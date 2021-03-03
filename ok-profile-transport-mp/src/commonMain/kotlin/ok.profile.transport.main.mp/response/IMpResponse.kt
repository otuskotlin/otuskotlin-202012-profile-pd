package ok.profile.transport.main.mp.response

import ok.profile.transport.main.mp.common.IMpDebug

interface IMpResponse {
    val responseId: String?
    val onRequest: String?
    val endTime: String?
    val errors: List<ErrorDto>?
    val status: ResponseStatusDto?
    val debug: IMpDebug?
}