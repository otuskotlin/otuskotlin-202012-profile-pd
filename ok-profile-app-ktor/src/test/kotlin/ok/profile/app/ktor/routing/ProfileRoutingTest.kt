package ok.profile.app.ktor.routing

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import ok.profile.app.ktor.module
import ok.profile.transport.main.mp.request.MpReadRequest
import ok.profile.transport.main.mp.response.MpReadResponse
import ok.profile.transport.main.mp.response.ResponseStatusDto
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

internal class ProfileRoutingTest {

    @Test
    fun get() {
        withTestApplication({ module(testing = true)}) {
            handleRequest(HttpMethod.Post, "/profile/read") {
                val body = MpReadRequest(
                    requestId = "req-id",
                    profileId = "test-id",
                    debug = MpReadRequest.Debug(stubCase = MpReadRequest.StubCase.SUCCESS),
                )
                val bodyString = Json.encodeToString(MpReadRequest.serializer(), body)
                setBody(bodyString)
                addHeader("Content-Type", "application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                val responseBody = Json.decodeFromString(MpReadResponse.serializer(), jsonString)

                assertEquals(ResponseStatusDto.SUCCESS, responseBody.status)
                assertEquals("test-id", responseBody.profileDto?.id)
            }
        }
    }

}