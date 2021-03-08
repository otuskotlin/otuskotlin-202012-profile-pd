package ok.profile.transport.mappers

import ok.profile.common.be.context.BeContext
import ok.profile.transport.main.mp.response.MpReadResponse

fun BeContext.createReadResponse(): MpReadResponse = MpReadResponse(
    profileDto = responseProfile.toTransport(),
)
