package ok.profile.common.be.models

class ProfileError(
    val group: Group = Group.NONE,
    val message: String = "",
) {
    enum class Group(val alias: String) {
        NONE(""),
        SERVER("internal-server"),
        REQUEST("bad-request")
    }
}