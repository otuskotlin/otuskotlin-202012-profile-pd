package dsl.models

inline class Phone(val phone: String) {
    companion object {
        val NONE = Phone("")
    }
}
