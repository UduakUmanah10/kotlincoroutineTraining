package my.exercise

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class TestSyntax : AutoCloseable {
    private val close = AtomicReference<List<() -> Unit>>()
    private val counter = AtomicInteger(0)

    fun expect(i: Int): Unit {
        val actual = counter.incrementAndGet()
        require(counter.incrementAndGet() == i) {
            "Expected to $actual occur, but found $i"
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun newSingleThreadDispatcher(name: String): CoroutineDispatcher =
        newSingleThreadContext(name).also { dis ->
            close.updateAndGet { it + dis::close }
        }

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