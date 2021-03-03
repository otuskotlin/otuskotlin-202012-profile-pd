package ok.profile.transport.main.mp.request

import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.common.MpWorkModeDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ReadRequestSerializationTest {

    private val request = MpReadRequest(
        profileId = "profile-1",
        requestId = "request-2",
        startTime = "2021-02-13T12:00:00",
        debug = MpReadRequest.Debug(MpWorkModeDto.PROD)
    )

    @Test
    fun serializationTest() {
        val json = Json {
            prettyPrint = true
        }

        val requestAsString = json.encodeToString(MpReadRequest.serializer(), request)

        assertTrue("profile-1" in requestAsString)
        assertTrue("PROD" in requestAsString)
        assertTrue("onResponse" !in requestAsString)
    }

    @Test
    fun deserializationTest() {
        val json = """
            {
                "profileId": "profile-1",
                "requestId": "request-2",
                "startTime": "2021-02-13T12:00:00",
                "debug": {
                    "mode": "PROD"
                }
            }
        """.trimIndent()

        val reqEntity = Json.decodeFromString(MpReadRequest.serializer(), json)
        assertEquals(reqEntity, request)
        assertEquals(request.debug?.mode, MpWorkModeDto.PROD)
    }
}