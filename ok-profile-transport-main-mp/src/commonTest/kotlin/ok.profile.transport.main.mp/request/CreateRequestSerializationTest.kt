package ok.profile.transport.main.mp.request

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class CreateRequestSerializationTest {

    private val request = MpCreateRequest(
        requestId = "id-1",
        startTime = "2021-02-13T12:00:00",
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

        val requestAsString = json.encodeToString(MpCreateRequest.serializer(), request)

        assertTrue("id-1" in requestAsString)
        assertTrue("Pavel" in requestAsString)
        assertTrue("onResponse" !in requestAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "requestId": "id-1",
                "startTime": "2021-02-13T12:00:00",
                "createData": {
                    "firstName": "Pavel",
                    "lastName": "Durov",
                    "email": "pavel@telegram.com"
                }
            }
        """.trimIndent()

        val reqEntity = Json.decodeFromString(MpCreateRequest.serializer(), json)

        assertEquals(reqEntity, request)
    }
}