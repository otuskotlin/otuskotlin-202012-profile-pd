package dsl

import dsl.models.UserModel
import dsl.models.UserPermissionsModel
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class DslTest {

    @Test
    fun createUserModel() {
        val user: UserModel = user {
            name {
                first = "Иван"
                second = "Иванович"
                last = "Ивавнов"
            }
            birth {
                date = LocalDate.parse("2000-01-01")
            }
            contacts {
                email = "ivan@ivanov.example"
                phone = "+7 999 999 9999"
            }

            permissions {
                add("READ")
                add(UserPermissionsModel.SEND_MESSAGE)
                +"UPDATE"
                +UserPermissionsModel.GET_NEWS
            }
        }

        assertEquals("Иванович", user.sname)
        assertEquals("2000-01-01", user.dob.toString())
        assertEquals("ivan@ivanov.example", user.email.email)
        assertTrue("permission must contain All permissions") {
            user.permissions.containsAll(listOf(
                UserPermissionsModel.READ,
                UserPermissionsModel.SEND_MESSAGE,
                UserPermissionsModel.UPDATE,
                UserPermissionsModel.GET_NEWS
            ))
        }
    }

    @Test
    fun scopeTest() {
        user {
            name{
//                name {
//
//                }
            }
        }
    }
}
