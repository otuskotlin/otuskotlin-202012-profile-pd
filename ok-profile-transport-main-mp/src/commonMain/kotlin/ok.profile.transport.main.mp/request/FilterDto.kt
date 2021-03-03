package ok.profile.transport.main.mp.request

import kotlinx.serialization.Serializable

@Serializable
data class FilterDto(
    val text: String? = null,
)
