package my.exercise

import kotlinx.coroutines.*

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        val job = launch/* Make sure the coroutine is started lazily */ {  expect(3) }
        expect(1)
        /* Make sure the lazy job is triggered */
        expect(2)
    }
