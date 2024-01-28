package my.exercise

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun MyLegacyAPI.fetch(): String =
        suspendCancellableCoroutine { cont ->

            val future = fetchData { data, exception ->
                when{
                    data !== null -> cont.resume(data)
                    exception !==null -> cont.resumeWithException(exception)
                    else -> cont.resumeWithException(IllegalStateException(""))
                }
                // resume the continuation puts valid value in the continuation
            }

            // note once the continuation has finished you cannot cancel the
            // operation because something that is finished cant be cancelled


            // cancel the future

        cont.invokeOnCancellation { future.cancel(true) }

        }


