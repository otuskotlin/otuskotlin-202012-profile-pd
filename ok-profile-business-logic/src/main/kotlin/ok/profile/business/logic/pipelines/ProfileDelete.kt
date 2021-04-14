package ok.profile.business.logic.pipelines

import ok.profile.business.logic.operations.CompletePipeline
import ok.profile.business.logic.operations.InitializePipeline
import ok.profile.business.logic.operations.stubs.ProfileDeleteStub
import ok.profile.business.logic.validators.ProfileIdValidator
import ok.profile.common.be.context.Context
import ok.profile.common.be.models.ProfileId
import ok.profile.pipeline.validation.validation
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.pipeline

object ProfileDelete : IOperation<Context> by pipeline({
    execute(InitializePipeline)
    execute(ProfileDeleteStub)

    validation {
        validate<ProfileId> {
            validator(ProfileIdValidator)
            on { requestProfileId }
        }
    }


    execute(CompletePipeline)
})