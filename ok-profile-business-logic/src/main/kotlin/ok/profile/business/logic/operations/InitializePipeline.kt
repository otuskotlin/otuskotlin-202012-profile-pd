package ok.profile.business.logic.operations

import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.operation

object InitializePipeline : IOperation<Context> by operation({
    startIf { status == ContextStatus.NONE }
    execute { status = ContextStatus.RUNNING }
})