package ok.profile.common.mp.validation

interface IValidator<T> {
    infix fun validate(sample: T): ValidationResult
}