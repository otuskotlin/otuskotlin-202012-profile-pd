package ok.profile.pipeline.validation

import ok.profile.common.mp.validation.IValidator
import ok.profile.common.mp.validation.ValidationResult

class DefaultValidationOperation<C, T>(
    private val onBlock: C.() -> T, // todo - можно попробовать сюда передать value
    private val validator: IValidator<T>,
    private var errorHandler: C.(ValidationResult) -> Unit = {}
): IValidationOperation<C, T> {
    override suspend fun execute(context: C) {
        val value = context.onBlock()
        val res = validator.validate(value)
        context.errorHandler(res)
    }
}