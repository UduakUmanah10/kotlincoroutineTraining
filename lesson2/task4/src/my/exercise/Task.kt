package my.exercise

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        val job = launch/* Fill in CoroutineStart */ { expect(1) }
        expect(2)
    }

