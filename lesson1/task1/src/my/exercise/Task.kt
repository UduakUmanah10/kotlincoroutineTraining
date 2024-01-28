package my.exercise

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


suspend fun cancellable(input: String): String {


    val result = withContext(Dispatchers.IO){
        input.reversed()
    }
    // Insert cancellation check
    return result
}
