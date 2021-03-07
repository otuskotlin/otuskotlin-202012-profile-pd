package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertTrue

internal class UpdateRequestSerializationTest : RequestSerializationTest<MpUpdateRequest>() {

    override val request = MpUpdateRequest(
        requestId = "id-1",
        updateData = MpProfileDto(
            id = "profile-1",
            firstName = "Pavel",
            lastName = "Durov",
            email = "pavel@telegram.com",
        ),
    )
    override val serializer: KSerializer<MpUpdateRequest> = MpUpdateRequest.serializer()

    @Test
    fun serializationTest() {
        assertTrue("id-1" in requestAsString)
        assertTrue("profile-1" in requestAsString)
        assertTrue("Durov" in requestAsString)
    }
}