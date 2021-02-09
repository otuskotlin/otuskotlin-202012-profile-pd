package ok.profile.common.mp

class Profile(
    val firstName: String,
    val secondName: String = "",
    val nickName: String = "$firstName#$secondName",
)
