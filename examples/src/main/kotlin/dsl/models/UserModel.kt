package dsl.models

import dsl.UserConfig
import dsl.UserDsl
import java.time.LocalDate

@UserDsl
data class UserModel(
    val id: UserId = UserId.NONE,
    val fname: String = "",
    val sname: String = "",
    val lname: String = "",
    val dob: LocalDate = LocalDate.MIN,
    val email: Email = Email.NONE,
    val phone: Phone = Phone.NONE,
    val permissions: MutableSet<UserPermissionsModel> = mutableSetOf(),
)
