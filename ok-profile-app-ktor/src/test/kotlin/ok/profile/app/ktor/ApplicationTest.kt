package ok.profile.app.ktor

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*

internal class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}