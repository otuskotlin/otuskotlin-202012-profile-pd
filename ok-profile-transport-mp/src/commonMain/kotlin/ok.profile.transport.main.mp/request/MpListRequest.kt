package ok.profile.transport.main.mp.request

import kotlinx.serialization.Serializable
import ok.profile.transport.main.mp.common.IMpDebug
import ok.profile.transport.main.mp.common.MpWorkModeDto

@Serializable
data class MpListRequest(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: Debug? = null,
    val filterData: FilterDto? = null,
) : IMpRequest {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto? = MpWorkModeDto.TEST
    ) : IMpDebug
}