package ok.profile.transport.main.mp.response

import kotlinx.serialization.Serializable
import ok.profile.transport.main.mp.common.IMpDebug
import ok.profile.transport.main.mp.common.MpWorkModeDto
import ok.profile.transport.main.mp.dto.MpProfileDto

@Serializable
data class MpCreateResponse(
    override val responseId: String? = null,
    override val onRequest: String? = null,
    override val endTime: String? = null,
    override val errors: List<ErrorDto>? = null,
    override val status: ResponseStatusDto? = null,
    override val debug: Debug? = null,
    val profileDto: MpProfileDto? = null,
) : IMpResponse {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto? = MpWorkModeDto.TEST
    ) : IMpDebug
}