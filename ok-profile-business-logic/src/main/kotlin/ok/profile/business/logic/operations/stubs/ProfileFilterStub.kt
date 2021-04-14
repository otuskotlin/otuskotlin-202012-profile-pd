package ok.profile.business.logic.operations.stubs

import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.ProfileId
import ok.profile.common.be.models.StubCase
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.operation

object ProfileFilterStub : IOperation<Context> by operation({
    startIf { stubCase == StubCase.FILTER_SUCCESS }

    execute {
        resultList = mutableListOf(
            Profile(
                id = ProfileId("test-id-1"),
                firstName = "Pavel",
                lastName = "Durov",
                email = "pavel@telega.org",
            ),
            Profile(
                id = ProfileId("test-id-2"),
                firstName = "Nikola",
                lastName = "Tesla",
                email = "tesla@telega.org",
            ),
        )
        status = ContextStatus.FINISHING
    }
})