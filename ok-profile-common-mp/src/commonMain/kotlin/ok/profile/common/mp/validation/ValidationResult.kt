package ok.profile.common.mp.validation

class ValidationResult(val errors: List<IValidationError>) {
    val isSuccess: Boolean
        get() = errors.isEmpty()

    companion object {
        val SUCCESS = ValidationResult(emptyList())
    }
}