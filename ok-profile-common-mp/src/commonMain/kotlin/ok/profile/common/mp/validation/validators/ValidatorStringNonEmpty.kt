package ok.profile.common.mp.validation.validators

import ok.profile.common.mp.validation.IValidator
import ok.profile.common.mp.validation.ValidationFieldError
import ok.profile.common.mp.validation.ValidationResult

class ValidatorStringNonEmpty(
    private val field: String = "",
    private val message: String = "String must not be empty"
): IValidator<String?> {

    override fun validate(sample: String?): ValidationResult {
        return if (sample.isNullOrBlank()) {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = message,
                    )
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }

}