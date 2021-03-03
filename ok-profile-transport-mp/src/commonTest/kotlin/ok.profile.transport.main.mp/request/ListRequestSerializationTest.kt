package ok.profile.transport.main.mp.request

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ListRequestSerializationTest {
    private val request = MpListRequest(
        requestId = "id-1",
        filterData = FilterDto(
            text = "example text"
        ),
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }

        val requestAsString = json.encodeToString(MpListRequest.serializer(), request)

        assertTrue("id-1" in requestAsString)
        assertTrue("example text" in requestAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "requestId": "id-1",
                "filterData": {
                    "text": "example text"
                }
            }
        """.trimIndent()

        val reqEntity = Json.decodeFromString(MpListRequest.serializer(), json)

        assertEquals(reqEntity, request)
    }
}