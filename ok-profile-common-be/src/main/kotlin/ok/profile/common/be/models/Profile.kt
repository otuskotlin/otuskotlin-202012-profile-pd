package ok.profile.common.be.models

data class Profile(
    val id: ProfileId = ProfileId.NONE,
    val firstName: String = "",
    val lastName: String = "",
    val nickName: String = "",
    val email: String = "",
    val avatar: String = "",
) {
    companion object {
        val NONE = Profile()
    }
}
