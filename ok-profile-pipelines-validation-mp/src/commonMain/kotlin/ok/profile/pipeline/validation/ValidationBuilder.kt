package ok.profile.pipeline.validation

import ok.profile.common.mp.validation.ValidationResult
import ok.profile.pipline.mp.IOperation
import ok.profile.pipline.mp.IOperationBuilder
import ok.profile.pipline.mp.PipelineDsl

@PipelineDsl
class ValidationBuilder<C>: IOperationBuilder<C> {
    private var checkPrecondition: suspend C.() -> Boolean = { true }
    private var errorHandler: C.(ValidationResult) -> Unit = {}
    private val validators: MutableList<IValidationOperation<C,*>> = mutableListOf()

    fun startIf(block: suspend C.() -> Boolean) {
        checkPrecondition = block
    }

    fun errorHandler(block: C.(ValidationResult) -> Unit) {
        errorHandler = block
    }

    fun <T> validate(block: ValidationOperationBuilder<C,T>.() -> Unit) {
        val builder = ValidationOperationBuilder<C,T>(errorHandler).apply(block)
        validators.add(builder.build())
    }

    override fun build(): IOperation<C> = PipelineValidation(validators)

}