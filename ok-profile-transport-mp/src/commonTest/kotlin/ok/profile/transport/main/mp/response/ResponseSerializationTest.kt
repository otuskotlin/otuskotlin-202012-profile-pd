package ok.profile.transport.main.mp.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal abstract class ResponseSerializationTest<T : IMpResponse> {
    private val json = Json { prettyPrint = true }
    protected abstract val serializer: KSerializer<T>
    protected abstract val response: T
    protected val responseAsString by lazy { json.encodeToString(serializer, response) }

    @Test
    fun deserializationTest() {
        val reqEntity = Json.decodeFromString(serializer, responseAsString)
        assertEquals(reqEntity, response)
    }
}