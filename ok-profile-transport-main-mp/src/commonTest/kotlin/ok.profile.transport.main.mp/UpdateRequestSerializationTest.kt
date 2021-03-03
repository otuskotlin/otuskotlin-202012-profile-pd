package ok.profile.transport.main.mp

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.dto.MpProfileDto
import ok.profile.transport.main.mp.request.MpUpdateRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class UpdateRequestSerializationTest {

    private val request = MpUpdateRequest(
        requestId = "id-1",
        profileId = "profile-1",
        createData = MpProfileDto(
            firstName = "Pavel",
            lastName = "Durov",
            email = "pavel@telegram.com",
        ),
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }
        val requestAsString = json.encodeToString(MpUpdateRequest.serializer(), request)
        assertTrue("id-1" in requestAsString)
        assertTrue("profile-1" in requestAsString)
        assertTrue("Durov" in requestAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "requestId": "id-1",
                "createData": {
                    "firstName": "Pavel",
                    "lastName": "Durov",
                    "email": "pavel@telegram.com"
                },
                "profileId": "profile-1"
            }    
        """.trimIndent()

        val reqEntity = Json.decodeFromString(MpUpdateRequest.serializer(), json)
        assertEquals(reqEntity, request)
    }
}