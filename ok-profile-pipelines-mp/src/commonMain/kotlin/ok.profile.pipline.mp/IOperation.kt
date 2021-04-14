package ok.profile.pipline.mp

interface IOperation<T> {
    suspend fun execute(context: T)
}