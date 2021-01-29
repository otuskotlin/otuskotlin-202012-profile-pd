package examples

public var x: Int = 5
    private set

open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // public по умолчанию

    protected class Nested {
        public val e: Int = 5
    }
}

fun main() {
    val outer = Outer()
    //val nested = Outer.Nested() //не компилируется
}
