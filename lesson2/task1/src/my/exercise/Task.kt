package my.exercise

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun TestSyntax.default(): Unit =
    runBlocking(newSingleThreadDispatcher("main")) {
        launch { delay(100); expect(/* expected occurrence */) }
        launch { delay(200); expect(/* expected occurrence */) }
        expect(/* expected occurrence */)
    }
