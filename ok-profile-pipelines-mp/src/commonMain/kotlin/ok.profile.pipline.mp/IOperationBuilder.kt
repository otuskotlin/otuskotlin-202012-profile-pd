package ok.profile.pipline.mp

interface IOperationBuilder<T> {
    fun build(): IOperation<T>
}