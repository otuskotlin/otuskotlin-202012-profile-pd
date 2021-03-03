package ok.profile.transport.main.mp.response

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class UpdateResponseSerializationTest {

    private val response = MpUpdateResponse(
        responseId = "id-1",
        errors = listOf(
            ErrorDto(
                code = "ERROR-CODE",
                level = ErrorDto.Level.ERROR
        )),
        profileDto = MpProfileDto(
            id = "profile-1",
            firstName = "Pavel",
            lastName = "Durov"
        )
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }

        val responseAsString = json.encodeToString(MpUpdateResponse.serializer(), response)

        assertTrue("id-1" in responseAsString)
        assertTrue("ERROR-CODE" in responseAsString)
        assertTrue("Pavel" in responseAsString)
        assertTrue("profile-1" in responseAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "responseId": "id-1",
                "errors": [
                    {
                        "code": "ERROR-CODE",
                        "level": "ERROR"
                    }
                ],
                "profileDto": {
                    "id": "profile-1"
                    "firstName": "Pavel",
                    "lastName": "Durov"
                }
            }           
        """.trimIndent()

        val respEntity = Json.decodeFromString(MpUpdateResponse.serializer(), json)
        assertEquals(respEntity, response)
    }
}