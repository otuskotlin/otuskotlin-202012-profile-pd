package ok.profile.transport.main.mp.request

import kotlinx.serialization.Serializable
import ok.profile.transport.main.mp.common.IMpDebug
import ok.profile.transport.main.mp.common.MpWorkModeDto
import ok.profile.transport.main.mp.dto.MpProfileDto

@Serializable
data class MpCreateRequest(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: Debug? = null,
    val createData: MpProfileDto? = null,
) : IMpRequest {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?
    ) : IMpDebug
}