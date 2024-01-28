package my.exercise

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.CoroutineContext

class TestSyntax() : AutoCloseable {
    private val close = AtomicReference<List<() -> Unit>>(emptyList())
    private val counter = AtomicInteger(0)

    fun expect(i: Int): Unit {
        val actual = counter.incrementAndGet()
        require(actual == i) {
            "Expected to $actual occur, but found $i"
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun newSingleThreadDispatcher(name: String): CoroutineContext =
        newSingleThreadContext(name)
            .also { dis -> close.updateAndGet { it + dis::close } }

    override fun close() {
        close.get()
            .mapNotNull { runCatching { it() }.exceptionOrNull() }
            .rethrow()
    }

    fun List<Throwable>.rethrow() {
        fold<Throwable, Throwable?>(null) { acc, it ->
            acc?.apply { addSuppressed(it) } ?: it
        }?.let { throw it }
    }
}