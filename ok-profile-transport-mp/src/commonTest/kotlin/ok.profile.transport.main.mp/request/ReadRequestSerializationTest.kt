package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import ok.profile.transport.main.mp.common.MpWorkModeDto
import kotlin.test.Test
import kotlin.test.assertTrue

internal class ReadRequestSerializationTest : RequestSerializationTest<MpReadRequest>() {

    override val request = MpReadRequest(
        profileId = "profile-1",
        requestId = "request-2",
        startTime = "2021-02-13T12:00:00",
        debug = MpReadRequest.Debug(MpWorkModeDto.PROD)
    )
    override val serializer: KSerializer<MpReadRequest> = MpReadRequest.serializer()

    @Test
    fun serializationTest() {
        assertTrue("profile-1" in requestAsString)
        assertTrue("PROD" in requestAsString)
        assertTrue("onResponse" !in requestAsString)
    }
}