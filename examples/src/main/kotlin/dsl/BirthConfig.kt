package dsl

import java.time.LocalDate

@UserDsl
class BirthConfig(
    var date: LocalDate = LocalDate.MIN
)
