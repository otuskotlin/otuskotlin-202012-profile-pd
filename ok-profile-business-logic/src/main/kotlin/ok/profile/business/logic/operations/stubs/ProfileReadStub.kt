package ok.profile.business.logic.operations.stubs

import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.common.be.models.Profile
import ok.profile.common.be.models.StubCase
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.operation

object ProfileReadStub : IOperation<Context> by operation({
    startIf { stubCase == StubCase.READ_SUCCESS }

    execute {
        responseProfile = Profile(
            id = requestProfileId,
            firstName = "Pavel",
            lastName = "Durov",
            email = "pavel@telega.org",
        )
        status = ContextStatus.FINISHING
    }
})