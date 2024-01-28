### Guaranteeing certain code to run

When _launching_ different coroutines, it's important that we get some intuition on when which tasks are running.

Sometimes it's critical that certain code runs, even in the face of cancellation.

Define the `CoroutineStart` parameter that satisfies the order of occurrence defined in the example.
