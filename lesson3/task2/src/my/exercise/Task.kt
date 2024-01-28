package my.exercise

import kotlinx.coroutines.*

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        supervisorScope {
            launch {
                delay(2000)
                expect(/* Order of occurrence */)
            }
            launch {
                delay(1000)
                expect(/* Order of occurrence */)
                throw RuntimeException("Boom")
            }
            expect(/* Order of occurrence */)
        }
        /* Expect or never */
    }
