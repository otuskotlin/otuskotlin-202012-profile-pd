package ok.profile.business.logic.pipelines

import ok.profile.business.logic.operations.CompletePipeline
import ok.profile.business.logic.operations.InitializePipeline
import ok.profile.business.logic.operations.stubs.ProfileCreateStub
import ok.profile.common.be.context.Context
import ok.profile.common.mp.validation.validators.ValidatorStringNonEmpty
import ok.profile.pipeline.validation.validation
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.pipeline

object ProfileCreate : IOperation<Context> by pipeline({
    execute(InitializePipeline)
    execute(ProfileCreateStub)

    validation {
        validate<String?> {
            validator(ValidatorStringNonEmpty(field = "firstName", message = "Profile first name most not be empty."))
            on { requestProfile.firstName }
        }
        validate<String?> {
            validator(ValidatorStringNonEmpty(field = "description", message = "Profile last name most not be empty."))
            on { requestProfile.lastName }
        }
    }

    execute(CompletePipeline)
})