package az.zero.azattendance.common

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

fun <T> executeFlow(
    showLoading: Boolean = true,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    action: suspend () -> T,
) = flow {
    if (showLoading) emit(ResponseState.Loading())
    val result = action()
    emit(ResponseState.Success(data = result))
}.catch {
    emit(ResponseState.Error(message = it.message ?: "Unknown Error"))
}.flowOn(dispatcher)


fun executeFlowCallback(
    collection: CollectionReference,
) = callbackFlow {
    val listenerRegistration = collection.addSnapshotListener { value, error ->
        if (error != null && value != null) {
            close()
            return@addSnapshotListener
        }

        if (value != null) trySend(value)
    }
    awaitClose {
        listenerRegistration.remove()
    }
}