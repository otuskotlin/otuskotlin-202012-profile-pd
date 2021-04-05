package ok.profile.pipeline.validation

import ok.profile.common.mp.test.runBlockingTest
import ok.profile.common.mp.validation.IValidationError
import ok.profile.common.mp.validation.ValidationResult
import ok.profile.common.mp.validation.validators.ValidatorStringNonEmpty
import ok.profile.pipline.mp.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidationCorTest {

    @Test
    fun pipelineValidation() {
        val pipeline = pipeline<TestContext> {

            validation {
                errorHandler { result: ValidationResult ->
                    if (result.isSuccess.not()) {
                        errors.addAll(result.errors)
                    }
                }

                validate<String?> { validator(ValidatorStringNonEmpty()); on { x } }
                validate<String?> { validator(ValidatorStringNonEmpty()); on { y } }

//                validate({ x }) {
//                    validator(ValidatorStringNonEmpty())
//                }
            }
        }

        runBlockingTest {
            val ctx = TestContext()
            pipeline.execute(ctx)
            assertEquals(2, ctx.errors.size)
        }
    }

    data class TestContext(
        val x: String = "",
        val y: String = "",
        val errors: MutableList<IValidationError> = mutableListOf(),
    )
}