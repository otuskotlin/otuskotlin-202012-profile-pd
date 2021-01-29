package dsl.models

inline class Email(val email: String) {
    companion object {
        val NONE = Email("")
    }
}
