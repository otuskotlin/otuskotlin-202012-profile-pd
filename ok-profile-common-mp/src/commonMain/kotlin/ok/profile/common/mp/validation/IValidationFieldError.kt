package ok.profile.common.mp.validation

interface IValidationFieldError : IValidationError {
    val field: String
}