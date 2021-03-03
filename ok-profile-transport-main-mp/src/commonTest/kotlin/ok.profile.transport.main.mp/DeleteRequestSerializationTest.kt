package ok.profile.transport.main.mp

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.request.MpDeleteRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class DeleteRequestSerializationTest {

    private val request = MpDeleteRequest(
        requestId = "id-1",
        profileId = "profile-1"
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }
        val requestAsString = json.encodeToString(MpDeleteRequest.serializer(), request)

        assertTrue("id-1" in requestAsString)
        assertTrue("profile-1" in requestAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "requestId": "id-1",
                "profileId": "profile-1"
            }
        """.trimIndent()

        val reqEntity = Json.decodeFromString(MpDeleteRequest.serializer(), json)

        assertEquals(reqEntity, request)
    }
}