package ok.profile.transport.main.mp.response

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.dto.MpProfileDto
import kotlin.test.Test
import kotlin.test.assertTrue

class ListResponseSerializationTest {

    private val response = MpListResponse(
        responseId = "id-1",
        profiles = listOf(
            MpProfileDto(id = "profile-1"),
            MpProfileDto(id = "profile-2"),
        ),
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }

        val responseAsString = json.encodeToString(MpListResponse.serializer(), response)

        assertTrue("id-1" in responseAsString)
        assertTrue("profile-2" in responseAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "responseId": "id-1",
                "profiles": [
                    {
                        "id": "profile-1"
                    },
                    {
                        "id": "profile-2"
                    }
                ]
            }            
        """.trimIndent()

        val respEntity = Json.decodeFromString(MpListResponse.serializer(), json)

        assertTrue(respEntity.profiles?.size == 2)
    }
}