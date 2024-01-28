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
    fun testHello(): Unit =
        TestSyntax().use {
            it.default()
        }
}
