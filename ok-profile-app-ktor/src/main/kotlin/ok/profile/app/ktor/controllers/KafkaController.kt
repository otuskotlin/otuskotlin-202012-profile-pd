package ok.profile.app.ktor.controllers

import io.ktor.application.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import ok.profile.business.logic.ProfileService
import ok.profile.common.be.context.Context
import ok.profile.transport.main.mp.request.*
import ok.profile.transport.main.mp.response.*
import ok.profile.transport.mappers.*
import ok.profile.transport.mappers.buildUpdateResponse
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory
import ru.datana.smart.common.ktor.kafka.KtorKafkaConsumer
import ru.datana.smart.common.ktor.kafka.kafka

fun Application.kafkaEndpoints(
    brokers: String,
    kafkaConsumer: Consumer<String, String>?,
    kafkaProducer: Producer<String, String>?,
    profileService: ProfileService,
) {
    val topicReadIn by lazy { environment.config.property("profile.kafka.topicReadIn").getString() }
    val topicReadOut by lazy { environment.config.property("profile.kafka.topicReadOut").getString() }

    val topicCreateIn by lazy { environment.config.property("profile.kafka.topicCreateIn").getString() }
    val topicCreateOut by lazy { environment.config.property("profile.kafka.topicCreateOut").getString() }

    val topicUpdateIn by lazy { environment.config.property("profile.kafka.topicUpdateIn").getString() }
    val topicUpdateOut by lazy { environment.config.property("profile.kafka.topicUpdateOut").getString() }

    val topicDeleteIn by lazy { environment.config.property("profile.kafka.topicDeleteIn").getString() }
    val topicDeleteOut by lazy { environment.config.property("profile.kafka.topicDeleteOut").getString() }

    val topicFilterIn by lazy { environment.config.property("profile.kafka.topicFilterIn").getString() }
    val topicFilterOut by lazy { environment.config.property("profile.kafka.topicFilterOut").getString() }

    val producer: Producer<String, String> = kafkaProducer ?: run {
        KafkaProducer(mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to brokers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ))
    }

    install(KtorKafkaConsumer)

    routing {
        kafkaController(
            topicReadIn,
            topicReadOut,
            topicCreateIn,
            topicCreateOut,
            topicUpdateIn,
            topicUpdateOut,
            topicDeleteIn,
            topicDeleteOut,
            topicFilterIn,
            topicFilterOut,
            kafkaConsumer = kafkaConsumer,
            kafkaProducer = producer,
            profileService = profileService,
        )
    }
}

fun Routing.kafkaController(
    topicReadIn: String,
    topicReadOut: String,
    topicCreateIn: String,
    topicCreateOut: String,
    topicUpdateIn: String,
    topicUpdateOut: String,
    topicDeleteIn: String,
    topicDeleteOut: String,
    topicFilterIn: String,
    topicFilterOut: String,
    kafkaConsumer: Consumer<String, String>?,
    kafkaProducer: Producer<String, String>,
    profileService: ProfileService,
) {
    val log = LoggerFactory.getLogger("ok.profile.app.ktor.controllers.Routing.kafkaController")

    kafka<String, String> {
        keyDeserializer = StringDeserializer::class.java
        valDeserializer = StringDeserializer::class.java
        consumer = kafkaConsumer

        topic(topicReadIn) {
            log.debug("Read profile. Got {} items", items.items.toList().size)
            for (item in items.items) {
                log.debug("Item - key : {}, value: {}", item.key, item.value)

                val request = Json.decodeFromString(MpReadRequest.serializer(), item.value)

                val context = Context().apply {
                    setRequest(request)
                }
                profileService.read(context)

                val response = context.buildReadResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
                val stringResponse = Json.encodeToString(MpReadResponse.serializer(), response)
                kafkaProducer.send(ProducerRecord(topicReadOut, stringResponse))
            }
        }

        topic(topicCreateIn) {
            log.debug("Create profile. Got {} items", items.items.toList().size)
            for (item in items.items) {
                log.debug("Item - key : {}, value: {}", item.key, item.value)

                val request = Json.decodeFromString(MpCreateRequest.serializer(), item.value)

                val context = Context().apply {
                    setRequest(request)
                }
                profileService.create(context)

                val response = context.buildCreateResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
                val stringResponse = Json.encodeToString(MpCreateResponse.serializer(), response)
                kafkaProducer.send(ProducerRecord(topicCreateOut, stringResponse))
            }
        }

        topic(topicUpdateIn) {
            log.debug("Update profile. Got {} items", items.items.toList().size)
            for (item in items.items) {
                log.debug("Item - key : {}, value: {}", item.key, item.value)

                val request = Json.decodeFromString(MpUpdateRequest.serializer(), item.value)

                val context = Context().apply {
                    setRequest(request)
                }
                profileService.update(context)

                val response = context.buildUpdateResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
                val stringResponse = Json.encodeToString(MpUpdateResponse.serializer(), response)
                kafkaProducer.send(ProducerRecord(topicUpdateOut, stringResponse))
            }
        }

        topic(topicDeleteIn) {
            log.debug("Delete profile. Got {} items", items.items.toList().size)
            for (item in items.items) {
                log.debug("Item - key : {}, value: {}", item.key, item.value)

                val request = Json.decodeFromString(MpDeleteRequest.serializer(), item.value)

                val context = Context().apply {
                    setRequest(request)
                }
                profileService.delete(context)

                val response = context.buildDeleteResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
                val stringResponse = Json.encodeToString(MpDeleteResponse.serializer(), response)
                kafkaProducer.send(ProducerRecord(topicDeleteOut, stringResponse))
            }
        }

        topic(topicFilterIn) {
            log.debug("Filter profile. Got {} items", items.items.toList().size)
            for (item in items.items) {
                log.debug("Item - key : {}, value: {}", item.key, item.value)

                val request = Json.decodeFromString(MpListRequest.serializer(), item.value)

                val context = Context().apply {
                    setRequest(request)
                }
                profileService.filter(context)

                val response = context.buildListResponse().copy(
                    onRequest = request.requestId,
                    responseId = "resp-id",
                    status = ResponseStatusDto.SUCCESS,
                )
                val stringResponse = Json.encodeToString(MpListResponse.serializer(), response)
                kafkaProducer.send(ProducerRecord(topicFilterOut, stringResponse))
            }
        }
    }
}