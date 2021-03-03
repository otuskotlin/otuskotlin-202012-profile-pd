package ok.profile.transport.main.mp.common

import kotlinx.serialization.Serializable

@Serializable
enum class MpWorkModeDto {
    PROD,
    TEST,
}
