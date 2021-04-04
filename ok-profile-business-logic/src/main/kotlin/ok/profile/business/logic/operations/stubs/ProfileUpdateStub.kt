package ok.profile.business.logic.operations.stubs

import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.common.be.models.StubCase
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.operation

object ProfileUpdateStub : IOperation<Context> by operation({
    startIf { stubCase == StubCase.UPDATE_SUCCESS }

    execute {
        responseProfile = requestProfile.copy()
        status = ContextStatus.FINISHING
    }
})