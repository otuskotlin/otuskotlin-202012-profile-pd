package ok.profile.transport.main.mp.request

import kotlinx.serialization.KSerializer
import kotlin.test.Test
import kotlin.test.assertTrue

internal class ListRequestSerializationTest : RequestSerializationTest<MpListRequest>() {
    override val request = MpListRequest(
        requestId = "id-1",
        filterData = FilterDto(
            text = "example text"
        ),
    )
    override val serializer: KSerializer<MpListRequest> = MpListRequest.serializer()

    @Test
    fun serializationTest() {
        assertTrue("id-1" in requestAsString)
        assertTrue("example text" in requestAsString)
    }
}