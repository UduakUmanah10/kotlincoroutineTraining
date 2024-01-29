package my.exercise

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        coroutineScope {
            launch {
                delay(2000)
                never()
            }
            launch {
                delay(1000)
                expect(2)
                throw RuntimeException("Boom")
            }
            expect(1)
        }
        never()
    }
