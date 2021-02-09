package ok.profile.common.mp

import kotlin.test.Test
import kotlin.test.assertEquals

class ProfileTest {

    @Test
    fun defaultNickNameTest() {
        val profile = Profile("Pavel", "Durov")
        assertEquals(profile.nickName, "${profile.firstName}#${profile.secondName}")
    }
}
