package ok.profile.transport.main.mp.request

import kotlinx.serialization.Serializable
import ok.profile.transport.main.mp.common.IMpDebug
import ok.profile.transport.main.mp.common.MpWorkModeDto

@Serializable
data class MpDeleteRequest(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: Debug? = null,
    val profileId: String? = null,
) : IMpRequest {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?
    ) : IMpDebug
}