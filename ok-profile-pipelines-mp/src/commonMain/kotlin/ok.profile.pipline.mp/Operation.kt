package ok.profile.pipline.mp

class Operation<T>(
    private val checkPrecondition: suspend T.() -> Boolean,
    private val runOperation: suspend T.() -> Unit,
    private val handleError: suspend T.(Throwable) -> Unit,
) : IOperation<T> {

    override suspend fun execute(context: T) {
        try {
            if (context.checkPrecondition()) context.runOperation()
        } catch (e: Throwable) {
            context.handleError(e)
        }
    }

    @PipelineDsl
    class Builder<T> : IOperationBuilder<T> {
        private var checkPrecondition: suspend T.() -> Boolean = { true }
        private var runOperation: suspend T.() -> Unit = {}
        private var handleError: suspend T.(Throwable) -> Unit = { throw it }

        fun startIf(block: suspend T.() -> Boolean) {
            checkPrecondition = block
        }

        fun execute(block: suspend T.() -> Unit) {
            runOperation = block
        }

        fun onError(block: suspend T.(Throwable) -> Unit) {
            handleError = block
        }

        override fun build(): Operation<T> =
            Operation(checkPrecondition, runOperation, handleError)
    }
}