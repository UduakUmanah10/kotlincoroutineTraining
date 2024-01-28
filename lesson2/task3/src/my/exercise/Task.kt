package my.exercise

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        val job = launch/* Fill in CoroutineStart */ { expect(2) }
        expect(1)
        job.cancelAndJoin()
        expect(3)
    }

