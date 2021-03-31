package ok.profile.transport.mappers

import ok.profile.common.be.context.Context
import ok.profile.transport.main.mp.response.*

fun Context.buildReadResponse(): MpReadResponse = MpReadResponse(
    profileDto = responseProfile.toTransport(),
)

fun Context.buildCreateResponse(): MpCreateResponse = MpCreateResponse(
    profileDto = responseProfile.toTransport()
)

fun Context.buildUpdateResponse(): MpUpdateResponse = MpUpdateResponse(
    profileDto = responseProfile.toTransport()
)

fun Context.buildDeleteResponse(): MpDeleteResponse = MpDeleteResponse(
    deleted = true,
    profileDto = responseProfile.toTransport()
)

fun Context.buildListResponse(): MpListResponse = MpListResponse(
    profiles = this.resultList.map { it.toTransport() }
)