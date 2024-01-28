### Wrapping foreign APIs

We often have to wrap foreign APIs, such as Java SDKs in a safe manner.

In this exercise we're going to be wrapping a Java callback based library method, and expose a nice Kotlin `suspend` API as an extension method.

The `MyLegacyAPI` library class exposes a `fetchData` function, that takes a Java Functional interface `Callback`.
Lucky Kotlin can automatically translate this to a lambda.

The lambda has _either_ a non-null `String` value, or a `non-null` `Exception` when an error occurred.

## Task 1

We need to `resume` the `Continuation`, in case of _success, and _failure_.

<div class="hint">

You can use the `Result` method, or the regular methods.  Both achieve the same.

</div>


## Task 2

The `fetchData` API returns a `Future`,
and in case when our `suspend` function is cancelled we also need to cancel the `Future`.

_invoke_ the cancellation of the `Future` on the `Continuation`. 