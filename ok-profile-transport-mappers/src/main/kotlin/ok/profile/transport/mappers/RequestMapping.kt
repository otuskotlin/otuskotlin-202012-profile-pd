package ok.profile.transport.mappers

import ok.profile.common.be.context.Context
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId
import ok.profile.common.be.models.StubCase
import ok.profile.transport.main.mp.dto.MpProfileDto
import ok.profile.transport.main.mp.request.*

fun Context.setRequest(request: IMpRequest) {
    when (request) {
        is MpCreateRequest -> {
            request.createData?.let(this::mapProfile)
            stubCase = when (request.debug?.stubCase) {
                MpCreateRequest.StubCase.SUCCESS -> StubCase.CREATE_SUCCESS
                else -> StubCase.NONE
            }
        }
        is MpReadRequest -> {
            request.profileId?.let(this::mapId)
            stubCase = when (request.debug?.stubCase) {
                MpReadRequest.StubCase.SUCCESS -> StubCase.READ_SUCCESS
                else -> StubCase.NONE
            }
        }
        is MpUpdateRequest -> {
            request.updateData?.let(this::mapProfile)
            request.updateData?.id?.let(this::mapId)
            stubCase = when (request.debug?.stubCase) {
                MpUpdateRequest.StubCase.SUCCESS -> StubCase.UPDATE_SUCCESS
                else -> StubCase.NONE
            }
        }
        is MpDeleteRequest -> {
            request.profileId?.let(this::mapId)
            stubCase = when (request.debug?.stubCase) {
                MpDeleteRequest.StubCase.SUCCESS -> StubCase.DELETE_SUCCESS
                else -> StubCase.NONE
            }
        }
        is MpListRequest -> {
            request.filterData?.let {
                filterText = it.text ?: ""
            }
            stubCase = when (request.debug?.stubCase) {
                MpListRequest.StubCase.SUCCESS -> StubCase.CREATE_SUCCESS
                else -> StubCase.NONE
            }
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