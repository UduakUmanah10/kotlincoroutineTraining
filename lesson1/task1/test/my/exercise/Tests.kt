package my.exercise

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit


class Test {
    @JvmField
    @Rule
    var globalTimeout = Timeout(5, TimeUnit.SECONDS)

    @Property(tries = 20)
    fun testHello(@ForAll data: String): Unit = runBlocking {
        assertEquals(cancellable(data), data.reversed())
    }

    @Test
    fun testCancellation(): Unit = runBlocking {
        launch(Dispatchers.Default) {
            while (true) {
                cancellable("data")
            }
        }.cancelAndJoin()
    }
}