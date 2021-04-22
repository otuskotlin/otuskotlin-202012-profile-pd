package ok.profile.app.ktor

import io.ktor.config.*
import io.ktor.server.testing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import ok.profile.transport.main.mp.request.MpReadRequest
import ok.profile.transport.main.mp.response.MpReadResponse
import ok.profile.transport.main.mp.response.ResponseStatusDto
import org.junit.Test
import ru.datana.smart.common.ktor.kafka.TestConsumer
import ru.datana.smart.common.ktor.kafka.TestProducer
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class KafkaTest {

    @Test
    fun `read profile test`() {
        val consumer: TestConsumer<String, String> = TestConsumer(duration = Duration.ofMillis(20))
        val producer: TestProducer<String, String> = TestProducer()
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("profile.kafka.topicReadIn", TOPIC_READ_IN)
                put("profile.kafka.topicReadOut", TOPIC_READ_OUT)

                put("profile.kafka.topicCreateIn", TOPIC_CREATE_IN)
                put("profile.kafka.topicCreateOut", TOPIC_CREATE_OUT)

                put("profile.kafka.topicUpdateIn", TOPIC_UPDATE_IN)
                put("profile.kafka.topicUpdateOut", TOPIC_UPDATE_OUT)

                put("profile.kafka.topicDeleteIn", TOPIC_DELETE_IN)
                put("profile.kafka.topicDeleteOut", TOPIC_DELETE_OUT)

                put("profile.kafka.topicFilterIn", TOPIC_FILTER_IN)
                put("profile.kafka.topicFilterOut", TOPIC_FILTER_OUT)

                put("profile.kafka.brokers", BROKERS)
            }
            module(
                kafkaTestConsumer = consumer,
                kafkaTestProducer = producer,
            )
        }) {
            runBlocking {
                delay(60L)

                val request = MpReadRequest(
                    requestId = "req-id",
                    profileId = "test-id",
                    debug = MpReadRequest.Debug(stubCase = MpReadRequest.StubCase.SUCCESS),
                )
                val stringRequest = Json.encodeToString(MpReadRequest.serializer(), request)


                consumer.send(TOPIC_READ_IN, "read-1", stringRequest)
                delay(100L)
                val responseObjs = producer.getSent()

                val responseBody = Json.decodeFromString(MpReadResponse.serializer(), responseObjs.first().value())
                assertEquals(ResponseStatusDto.SUCCESS, responseBody.status)
                assertEquals("test-id", responseBody.profileDto?.id)
            }
        }
    }

    companion object {
        const val TOPIC_READ_IN = "read-in"
        const val TOPIC_READ_OUT = "read-out"

        const val TOPIC_CREATE_IN = "create-in"
        const val TOPIC_CREATE_OUT = "create-out"

        const val TOPIC_UPDATE_IN = "update-in"
        const val TOPIC_UPDATE_OUT = "update-out"

        const val TOPIC_DELETE_IN = "delete-in"
        const val TOPIC_DELETE_OUT = "delete-out"

        const val TOPIC_FILTER_IN = "filter-in"
        const val TOPIC_FILTER_OUT = "filter-out"

        const val BROKERS = ""
    }
}