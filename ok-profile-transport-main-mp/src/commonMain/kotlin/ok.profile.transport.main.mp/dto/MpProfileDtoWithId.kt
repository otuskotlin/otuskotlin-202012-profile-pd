package ok.profile.transport.main.mp.dto

import kotlinx.serialization.Serializable

@Serializable
class MpProfileDtoWithId(
    val id: String? = null,
    val profile: MpProfileDto? = null,
)