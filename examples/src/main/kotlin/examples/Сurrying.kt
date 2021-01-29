package examples

// Описать в help и написать статью!!!

// попытаюсь объяснить, что такое каррирование функции

data class Width(val width: Double)
data class Length(val length: Double)
data class Height(val height: Double)

// у нас есть функция вычисления объема кирпича
fun getBrickVolume(width: Width, length: Length, height: Height) = width.width * length.length * height.height

// каррированная функция
val currying = { width: Width ->
    { length: Length ->
        { height: Height ->
            width.width * length.length * height.height
        }
    }
}

fun main() {
    // допустим ширина и длинна у нас всегда одинаковы и было бы глупо каждый раз передавать эти значения в функцию getBrickVolume
    val v1 = getBrickVolume(Width(5.0), Length(3.0), Height(2.0))
    val v2 = getBrickVolume(Width(5.0), Length(3.0), Height(1.0))
    val v3 = getBrickVolume(Width(5.0), Length(3.0), Height(3.0))

    println("""
        v1 = $v1
        v2 = $v2
        v3 = $v3
    """.trimIndent())

    // каррированный результат
    val f = currying(Width(5.0))(Length(3.0))

    val volume1 = f(Height(2.0))
    val volume2 = f(Height(1.0))
    val volume3 = f(Height(3.0))

    println("""
        volume1 = $volume1
        volume2 = $volume2
        volume3 = $volume3
    """.trimIndent())
}

