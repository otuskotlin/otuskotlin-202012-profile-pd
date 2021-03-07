package ok.profile.transport.main.mp.response

import kotlinx.serialization.KSerializer
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertTrue

internal class CreateResponseSerializationTest : ResponseSerializationTest<MpCreateResponse>() {

    override val response = MpCreateResponse(
        responseId = "id-1",
        errors = listOf(
            ErrorDto(
                code = "ERROR-CODE",
                level = ErrorDto.Level.ERROR
            )
        ),
        profileDto = MpProfileDto(
            id = "profile-1",
            firstName = "Pavel",
            lastName = "Durov"
        )
    )
    override val serializer: KSerializer<MpCreateResponse> = MpCreateResponse.serializer()

    @Test
    fun serializationTest() {
        assertTrue("id-1" in responseAsString)
        assertTrue("ERROR-CODE" in responseAsString)
        assertTrue("Pavel" in responseAsString)
        assertTrue("profile-1" in responseAsString)
    }
}