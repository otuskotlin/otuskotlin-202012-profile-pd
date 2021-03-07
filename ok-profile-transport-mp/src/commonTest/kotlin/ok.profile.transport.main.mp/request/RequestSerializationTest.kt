package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal abstract class RequestSerializationTest<T : IMpRequest> {
    private val json = Json { prettyPrint = true }
    protected abstract val serializer: KSerializer<T>
    protected abstract val request: T
    protected val requestAsString by lazy { json.encodeToString(serializer, request) }

    @Test
    fun deserializationTest() {
        val reqEntity = Json.decodeFromString(serializer, requestAsString)
        assertEquals(reqEntity, request)
    }
}