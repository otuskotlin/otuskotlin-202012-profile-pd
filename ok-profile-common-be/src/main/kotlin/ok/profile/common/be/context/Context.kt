package ok.profile.common.be.context

import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileError
import ok.profile.common.be.models.ProfileId
import ok.profile.common.be.models.StubCase

data class Context(
    var status: ContextStatus = ContextStatus.NONE,

    var requestProfileId: ProfileId = ProfileId.NONE,
    var requestProfile: Profile = Profile.NONE,

    var responseProfileId: ProfileId = ProfileId.NONE,
    var responseProfile: Profile = Profile.NONE,

    var filterText: String = "",
    var resultList: MutableList<Profile> = mutableListOf(),

    var errors: MutableList<ProfileError> = mutableListOf(),
    var stubCase: StubCase = StubCase.NONE,
)
