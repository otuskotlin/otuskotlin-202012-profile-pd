package example

interface UserDetails {
    fun getUsername(): String
    fun getPassword(): String
}

data class User(
    private val username: String,
    private val password: String,
) : UserDetails {
    override fun getUsername() = username
    override fun getPassword() = password
}
