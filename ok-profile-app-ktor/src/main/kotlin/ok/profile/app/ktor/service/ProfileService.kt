package ok.profile.app.ktor.service

import ok.profile.common.be.context.BeContext
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId

class ProfileService {
    private val profile = Profile(
        ProfileId("test-id"),
        firstName = "Pavel",
        lastName = "Durov",
        nickName = "pasha",
        email = "pavel@telegram.com",
        avatar = "fake-avatar",
    )

    fun get(context: BeContext) = context.run {
        responseProfile = profile
    }

}