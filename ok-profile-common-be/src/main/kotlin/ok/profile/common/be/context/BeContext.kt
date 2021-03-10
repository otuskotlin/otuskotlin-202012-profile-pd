package ok.profile.common.be.context

import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId

data class BeContext(
    var requestProfileId: ProfileId = ProfileId.NONE,
    var requestProfile: Profile = Profile.NONE,

    var responseProfileId: ProfileId = ProfileId.NONE,
    var responseProfile: Profile = Profile.NONE,

    var filterText: String = "",
    var resultList: MutableList<Profile> = mutableListOf()
)
