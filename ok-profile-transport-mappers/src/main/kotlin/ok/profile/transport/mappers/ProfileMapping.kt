package ok.profile.transport.mappers

import ok.profile.common.be.models.Profile
import ok.profile.transport.main.mp.dto.MpProfileDto

internal fun Profile.toTransport() = MpProfileDto(
    id = this.id.id,
    firstName = this.firstName,
    lastName = this.lastName,
    nickName = this.nickName,
    email = this.email,
)