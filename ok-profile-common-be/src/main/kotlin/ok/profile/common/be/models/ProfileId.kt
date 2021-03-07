package ok.profile.common.be.models

inline class ProfileId(
    val id: String,
) {
    companion object {
        val NONE = ProfileId(
            id = "",
        )
    }
}
