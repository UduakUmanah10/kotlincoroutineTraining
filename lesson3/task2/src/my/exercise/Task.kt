package my.exercise

import kotlinx.coroutines.*

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        supervisorScope {
            launch {
                delay(2000)
                expect(3)
            }
            launch {
                delay(1000)
                expect(2)
                throw RuntimeException("Boom")
            }
            expect(1)
        }
        expect(4)
    }
