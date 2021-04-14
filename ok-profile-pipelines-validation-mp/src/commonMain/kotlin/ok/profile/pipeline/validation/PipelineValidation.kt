package ok.profile.pipeline.validation

import ok.profile.pipline.mp.IOperation

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C,*>>,
    private val checkPrecondition: suspend C.() -> Boolean = { true },
) : IOperation<C> {
    override suspend fun execute(context: C) {
        if (context.checkPrecondition()) {
            validations.forEach {
                it.execute(context)
            }
        }
    }
}