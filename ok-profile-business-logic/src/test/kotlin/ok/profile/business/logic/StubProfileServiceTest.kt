package ok.profile.business.logic

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId
import ok.profile.common.be.models.StubCase

class StubProfileServiceTest : FunSpec({

    test("create new profile") {
        val profileService: IProfileService = ProfileService()
        val context = Context(
            requestProfile = Profile(
                firstName = "Pavel",
                lastName = "Durov",
                email = "pavel@telega.org"
            ),
            stubCase = StubCase.CREATE_SUCCESS,
        )

        profileService.create(context)

        context.status shouldBe ContextStatus.SUCCESS
        context.responseProfile shouldNotBe Profile.NONE
        with(context.responseProfile) {
            id shouldBe ProfileId("test-id")
            firstName shouldBe "Pavel"
            lastName shouldBe "Durov"
            email shouldBe "pavel@telega.org"
        }
    }

    test("read profile") {
        val profileService: IProfileService = ProfileService()
        val context = Context(
            requestProfileId = ProfileId("test-id"),
            stubCase = StubCase.READ_SUCCESS,
        )

        profileService.read(context)

        context.status shouldBe ContextStatus.SUCCESS
        context.responseProfile shouldNotBe Profile.NONE
        with(context.responseProfile) {
            id shouldBe ProfileId("test-id")
            firstName shouldBe "Pavel"
            lastName shouldBe "Durov"
            email shouldBe "pavel@telega.org"
        }
    }

    test("update profile") {
        val profileService: IProfileService = ProfileService()
        val context = Context(
            requestProfile = Profile(
                id = ProfileId("test-id"),
                firstName = "Pavel",
                lastName = "Durov",
                email = "pavel@telega.org"
            ),
            stubCase = StubCase.UPDATE_SUCCESS,
        )

        profileService.update(context)

        context.status shouldBe ContextStatus.SUCCESS
        context.responseProfile shouldNotBe Profile.NONE
        with(context.responseProfile) {
            id shouldBe ProfileId("test-id")
            firstName shouldBe "Pavel"
            lastName shouldBe "Durov"
            email shouldBe "pavel@telega.org"
        }
    }

    test("delete profile") {
        val profileService: IProfileService = ProfileService()
        val context = Context(
            requestProfileId = ProfileId("test-id"),
            stubCase = StubCase.DELETE_SUCCESS,
        )

        profileService.delete(context)

        context.status shouldBe ContextStatus.SUCCESS
        context.responseProfile shouldNotBe Profile.NONE
        with(context.responseProfile) {
            id shouldBe ProfileId("test-id")
            firstName shouldBe "Pavel"
            lastName shouldBe "Durov"
            email shouldBe "pavel@telega.org"
        }
    }

    test("filter profiles") {
        val profileService: IProfileService = ProfileService()
        val context = Context(
            filterText = "all",
            stubCase = StubCase.FILTER_SUCCESS,
        )

        profileService.filter(context)

        context.status shouldBe ContextStatus.SUCCESS
        context.resultList.size shouldBeGreaterThan 1
    }

})
