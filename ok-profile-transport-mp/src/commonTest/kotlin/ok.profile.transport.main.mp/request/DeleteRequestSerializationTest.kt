package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import kotlin.test.Test
import kotlin.test.assertTrue

internal class DeleteRequestSerializationTest : RequestSerializationTest<MpDeleteRequest>() {

    override val serializer: KSerializer<MpDeleteRequest> = MpDeleteRequest.serializer()
    override val request = MpDeleteRequest(
        requestId = "id-1",
        profileId = "profile-1"
    )

    @Test
    fun serializationTest() {
        assertTrue("id-1" in requestAsString)
        assertTrue("profile-1" in requestAsString)
    }
}