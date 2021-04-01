package ok.profile.pipline.mp

import ok.profile.common.mp.test.runBlockingTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class PipelineTest {

    data class SimpleContext(
        var name: String = "",
        var count: Long = 100,
    )

    @Test
    fun simplePipeline() {
        val context = SimpleContext(name = "Pavel", count = 1000)

        val pipeline = pipeline<SimpleContext> {
            startIf {
                name == "Pavel"
            }

            execute {
                count += 999
            }
        }

        runBlockingTest {
            pipeline.execute(context)
        }

        assertTrue {
            context.count == 1999L
        }

    }

    @Test
    fun nestedPipeline() {
        val context = SimpleContext(name = "Pavel", count = 1000)

        val pipeline = pipeline<SimpleContext> {
            startIf {
                name == "Pavel"
            }

            execute {
                name += " Durov"
            }

            pipeline {
                startIf {
                    name == "Pavel Durov"
                }

                execute {
                    count += 999
                }
            }
        }

        runBlockingTest {
            pipeline.execute(context)
        }

        assertTrue {
            context.name == "Pavel Durov" && context.count == 1999L
        }
    }
}