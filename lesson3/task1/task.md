### CoroutineScope

Structured Concurrency guarantees us that no jobs are leaked, and exceptions are properly propagated across async boundaries.

Try reasoning about the order of execution of following example,
and try changing the code to see if you can change the order of occurrence. Recover from errors, etc.
