package ok.profile.transport.mappers

import ok.profile.common.be.context.BeContext
import ok.profile.transport.main.mp.response.*

fun BeContext.buildReadResponse(): MpReadResponse = MpReadResponse(
    profileDto = responseProfile.toTransport(),
)

fun BeContext.buildCreateResponse(): MpCreateResponse = MpCreateResponse(
    profileDto = responseProfile.toTransport()
)

fun BeContext.buildUpdateResponse(): MpUpdateResponse = MpUpdateResponse(
    profileDto = responseProfile.toTransport()
)

fun BeContext.buildDeleteResponse(): MpDeleteResponse = MpDeleteResponse(
    deleted = true,
    profileDto = responseProfile.toTransport()
)

fun BeContext.buildListResponse(): MpListResponse = MpListResponse(
    profiles = this.resultList.map { it.toTransport() }
)