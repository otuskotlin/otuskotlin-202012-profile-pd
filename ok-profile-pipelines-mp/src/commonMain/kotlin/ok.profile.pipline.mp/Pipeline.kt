package ok.profile.pipline.mp

class Pipeline<T>
private constructor(
    private val operations: Collection<IOperation<T>>,
    private val checkPrecondition: suspend T.() -> Boolean,
    private val handleError: suspend T.(Throwable) -> Unit,
) : IOperation<T> {
    override suspend fun execute(context: T) {
        try {
            if (checkPrecondition(context)) operations.forEach { it.execute(context) }
        } catch (throwable: Throwable) {
            handleError(context, throwable)
        }
    }

    @PipelineDsl
    class Builder<T>: IOperationBuilder<T> {
        private val operations: MutableList<IOperation<T>> = mutableListOf()
        private var checkPrecondition: suspend T.() -> Boolean = { true }
        private var handleError: suspend T.(Throwable) -> Unit = { throw it }

        fun execute(operation: IOperation<T>) {
            operations.add(operation)
        }

        fun execute(block: suspend T.() -> Unit) {
            execute(Operation.Builder<T>().apply { execute(block) }.build())
        }

        fun startIf(block: suspend T.() -> Boolean) {
            checkPrecondition = block
        }

        fun onError(block: suspend T.(Throwable) -> Unit) {
            handleError = block
        }

        override fun build(): Pipeline<T> = Pipeline(operations, checkPrecondition, handleError)
    }
}

