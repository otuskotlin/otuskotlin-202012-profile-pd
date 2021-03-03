package ok.profile.transport.main.mp.response

import kotlinx.serialization.Serializable

@Serializable
enum class ResponseStatusDto {
    SUCCESS,
    BAD_REQUEST,
    INTERNAL_SERVER_ERROR,
    NOT_FOUND,
}