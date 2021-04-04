package ok.profile.business.logic.operations

import ok.profile.common.be.context.Context
import ok.profile.common.be.context.ContextStatus
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.operation
import ok.profile.pipline.mp.pipeline

object CompletePipeline : IOperation<Context> by pipeline({
    operation {
        startIf { status in setOf(ContextStatus.RUNNING, ContextStatus.FINISHING) }
        execute { status = ContextStatus.SUCCESS }
    }
    operation {
        startIf { status != ContextStatus.SUCCESS }
        execute { status = ContextStatus.ERROR }
    }
})