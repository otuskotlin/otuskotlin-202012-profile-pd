package ok.profile.app.ktor.service

import ok.profile.common.be.context.Context
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId

class InMemoryProfileService : ProfileService {
    private val mockProfiles = mutableListOf(
        Profile(
            ProfileId("test-id"),
            firstName = "Pavel",
            lastName = "Durov",
            nickName = "pasha",
            email = "pavel@telegram.com",
            avatar = "fake-avatar",
        ),
    )

    override fun get(context: Context) = context.run {
        responseProfile = mockProfiles.find { it.id == requestProfileId } ?: Profile.NONE
    }

    override fun create(context: Context) = context.run {
        mockProfiles += requestProfile
        responseProfile = requestProfile
    }

    override fun update(context: Context) {
        val current = mockProfiles.find { it.id == context.requestProfileId }
        if (current == null) {
            context.responseProfileId = ProfileId.NONE
            return
        }

        val updated = current.update(context.requestProfile)

        val index = mockProfiles.indexOf(current)
        mockProfiles[index] = updated

        context.responseProfile = updated
    }

    override fun delete(context: Context) {
        val isRemoved = mockProfiles.removeIf {
            it.id == context.requestProfileId
        }
        context.responseProfileId = if (isRemoved) context.requestProfileId else ProfileId.NONE
    }

    override fun filter(context: Context) {
        context.resultList = mockProfiles.filter { it.toString().contains(context.filterText) }.toMutableList()
    }

    private fun <T> createProperty(consumer: T, producer: T): T {
        return if (consumer == producer) consumer else producer
    }

    private fun Profile.update(new: Profile): Profile {
        return this.copy(
            firstName = createProperty(this.firstName, new.firstName),
            lastName = createProperty(this.lastName, new.lastName),
            nickName = createProperty(this.nickName, new.nickName),
            email = createProperty(this.email, new.email),
            avatar = createProperty(this.avatar, new.avatar),
        )
    }

}