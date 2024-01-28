package my.exercise

import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit


class Test {
    @JvmField
    @Rule
    var globalTimeout = Timeout(5, TimeUnit.SECONDS)

    @Test
    fun testHello(): Unit {
        val actual = runCatching {
            TestSyntax(2).use {
                it.default()
            }
        }.onFailure { if (it !is RuntimeException) throw it }
            .exceptionOrNull()
        require(actual is RuntimeException) {
            "Expected RuntimeException but found exit 0"
        }
    }
}