package ok.profile.app.ktor.service

import ok.profile.common.be.context.BeContext
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId

class ProfileService {
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

    fun get(context: BeContext) = context.apply {
        responseProfile = mockProfiles.find { it.id == requestProfileId } ?: Profile.NONE
    }

    fun create(context: BeContext) = context.apply {
        val created = Profile(
            ProfileId("test-id-1"),
            firstName = "Elon",
            lastName = "Musk",
            nickName = "elon",
            email = "elon@tesla.com",
            avatar = "fake-avatar",
        )
        mockProfiles += created
        responseProfile = created
    }

    fun update(context: BeContext) {
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

    fun delete(context: BeContext) {
        val isRemoved = mockProfiles.removeIf {
            it.id == context.requestProfileId
        }
        context.responseProfileId = if (isRemoved) context.requestProfileId else ProfileId.NONE
    }

    fun filter(context: BeContext) {
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