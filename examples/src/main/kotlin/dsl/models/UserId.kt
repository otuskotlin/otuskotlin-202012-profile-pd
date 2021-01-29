package dsl.models

inline class UserId(val id: String) {
    companion object {
        val NONE = UserId("")
    }
}
