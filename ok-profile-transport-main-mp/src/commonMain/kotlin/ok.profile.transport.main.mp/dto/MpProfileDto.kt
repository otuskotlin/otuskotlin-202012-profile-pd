package ok.profile.transport.main.mp.dto

import kotlinx.serialization.Serializable

@Serializable
data class MpProfileDto(
    val firstName: String? = null,
    val lastName: String? = null,
    val nickName: String? = null,
    val email: String? = null,
    val avatar: String? = null,
)