package ok.profile.transport.mappers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import ok.profile.common.be.context.BeContext
import ok.profile.common.be.models.ProfileId
import ok.profile.transport.main.mp.dto.MpProfileDto
import ok.profile.transport.main.mp.request.*

internal class RequestMappingTest : StringSpec({

    "create request should be mapped to context" {
        val request = MpCreateRequest(
            requestId = "id-1",
            createData = MpProfileDto(
                firstName = "Pavel",
                lastName = "Durov",
                email = "pavel@telegram.com",
                nickName = "xxxAAA",
                avatar = "avatar-url",
            ),
        )

        val context = BeContext()
        context.setRequest(request)

        context.requestProfileId shouldBe ProfileId.NONE
        context.requestProfile should {
            it.firstName shouldBe "Pavel"
            it.lastName shouldBe "Durov"
            it.email shouldBe "pavel@telegram.com"
            it.nickName shouldBe "xxxAAA"
            it.avatar shouldBe "avatar-url"
        }
    }

    "read request should be mapped to context" {
        val request = MpReadRequest(
            requestId = "id-1",
            profileId = "profile-1",
        )

        val context = BeContext()
        context.setRequest(request)

        context.requestProfileId shouldBe ProfileId("profile-1")
    }

    "update request should be mapped to context" {
        val request = MpUpdateRequest(
            requestId = "id-1",
            createData = MpProfileDto(
                id = "id-1",
                firstName = "Pavel",
                lastName = "Durov",
                email = "pavel@telegram.com",
                nickName = "xxxAAA",
                avatar = "avatar-url",
            ),
        )

        val context = BeContext()
        context.setRequest(request)

        context.requestProfileId shouldBe ProfileId("id-1")
        context.requestProfile should {
            it.id shouldBe ProfileId("id-1")
            it.firstName shouldBe "Pavel"
            it.lastName shouldBe "Durov"
            it.email shouldBe "pavel@telegram.com"
            it.nickName shouldBe "xxxAAA"
            it.avatar shouldBe "avatar-url"
        }
    }

    "delete request should be mapped to context" {
        val request = MpDeleteRequest(
            requestId = "id-1",
            profileId = "profile-1",
        )

        val context = BeContext()
        context.setRequest(request)

        context.requestProfileId shouldBe ProfileId("profile-1")
    }
})
