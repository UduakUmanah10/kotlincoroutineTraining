package my.exercise

import kotlinx.coroutines.*
import kotlinx.coroutines.withTimeout
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import org.junit.Assert
import org.junit.Assert.assertEquals
import java.lang.AssertionError
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture
import kotlin.time.Duration.Companion.seconds

class Test {
    @Property(tries = 20)
    fun testHello(
            @ForAll data: String
    ): Unit = runBlocking {
        val actual = MyLegacyAPI {
            it.call(data, null)
            CompletableFuture.runAsync { }
        }.fetch()
        assertEquals(data, actual)
    }

    @Property(tries = 20)
    fun testException(
            @ForAll message: String
    ): Unit = runBlocking {
        val actual = runCatching {
            MyLegacyAPI {
                it.call(null, RuntimeException(message))
                CompletableFuture.runAsync { }
            }.fetch()
        }
        assertEquals(message, actual.exceptionOrNull()?.message)
    }

    @Property(tries = 5)
    fun testCancellation(
            @ForAll message: String
    ): Unit = runBlocking {
        val completableFuture = CompletableFuture<Void?>()
        val job = launch(start = CoroutineStart.UNDISPATCHED) {
            MyLegacyAPI {
                completableFuture
            }.fetch()
        }
        delay(500)
        job.cancel()
        withTimeoutOrNull(2.seconds) {
            var isTrue = false
            do {
                isTrue = completableFuture.isCancelled
                yield()
            } while (!isTrue)
        } ?: throw AssertionError("LegacyAPI's Future was not cancelled")
    }
}