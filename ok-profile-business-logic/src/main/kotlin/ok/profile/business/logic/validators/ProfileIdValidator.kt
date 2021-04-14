package ok.profile.business.logic.validators

import ok.profile.common.be.models.ProfileId
import ok.profile.common.mp.validation.IValidator
import ok.profile.common.mp.validation.ValidationFieldError
import ok.profile.common.mp.validation.ValidationResult

object ProfileIdValidator : IValidator<ProfileId> {
    override fun validate(sample: ProfileId): ValidationResult {
        return if (sample == ProfileId.NONE) {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = "profileId",
                        message = "Profile id must not be empty.",
                    )
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }
}