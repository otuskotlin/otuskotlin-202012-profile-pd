package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertTrue

internal class CreateRequestSerializationTest : RequestSerializationTest<MpCreateRequest>() {

    override val request = MpCreateRequest(
        requestId = "id-1",
        startTime = "2021-02-13T12:00:00",
        createData = MpProfileDto(
            firstName = "Pavel",
            lastName = "Durov",
            email = "pavel@telegram.com",
        ),
    )
    override val serializer: KSerializer<MpCreateRequest> = MpCreateRequest.serializer()

    @Test
    fun serializationTest() {
        assertTrue("id-1" in requestAsString)
        assertTrue("Pavel" in requestAsString)
        assertTrue("onResponse" !in requestAsString)
    }
}