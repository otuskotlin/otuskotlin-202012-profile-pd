package ok.profile.transport.main.mp.response

import kotlinx.serialization.KSerializer
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertTrue

internal class ListResponseSerializationTest : ResponseSerializationTest<MpListResponse>() {

    override val response = MpListResponse(
        responseId = "id-1",
        profiles = listOf(
            MpProfileDto(id = "profile-1"),
            MpProfileDto(id = "profile-2"),
        ),
    )
    override val serializer: KSerializer<MpListResponse> = MpListResponse.serializer()

    @Test
    fun serializationTest() {
        assertTrue("id-1" in responseAsString)
        assertTrue("profile-2" in responseAsString)
    }
}