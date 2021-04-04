package ok.profile.business.logic.pipelines

import ok.profile.business.logic.operations.CompletePipeline
import ok.profile.business.logic.operations.InitializePipeline
import ok.profile.business.logic.operations.stubs.ProfileReadStub
import ok.profile.common.be.context.Context
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.pipeline

object ProfileRead : IOperation<Context> by pipeline({
    execute(InitializePipeline)
    execute(ProfileReadStub)
    execute(CompletePipeline)
})