package ok.profile.transport.mappers

import ok.profile.common.be.context.Context
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId
import ok.profile.transport.main.mp.dto.MpProfileDto
import ok.profile.transport.main.mp.request.*

fun Context.setRequest(request: IMpRequest) {
    when (request) {
        is MpCreateRequest -> request.createData?.let(this::mapProfile)
        is MpReadRequest -> request.profileId?.let(this::mapId)
        is MpUpdateRequest -> {
            request.updateData?.let(this::mapProfile)
            request.updateData?.id?.let(this::mapId)
        }
        is MpDeleteRequest -> request.profileId?.let(this::mapId)
        is MpListRequest -> request.filterData?.let {
            filterText = it.text ?: ""
        }
    }
}

private fun Context.mapId(profileId: String) {
    requestProfileId = ProfileId(profileId)
}

private fun Context.mapProfile(profileDto: MpProfileDto) {
    requestProfile = Profile(
        id = profileDto.id?.let { ProfileId(it) } ?: ProfileId.NONE,
        firstName = profileDto.firstName.orEmpty(),
        lastName = profileDto.lastName.orEmpty(),
        nickName = profileDto.nickName.orEmpty(),
        email = profileDto.email.orEmpty(),
        avatar = profileDto.avatar.orEmpty(),
    )
}