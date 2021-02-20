package ok.profile.common.mp

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProfileTest {

    @Test
    fun defaultNickNameTest() {
        val profile = Profile("Pavel", "Durov")
        assertTrue(profile.nickName.contains("Pavel"))
        assertTrue(profile.nickName.contains("Durov"))
    }
}
