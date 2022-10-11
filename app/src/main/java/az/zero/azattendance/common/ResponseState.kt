package az.zero.azattendance.common

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null, message: String? = null) :
        ResponseState<T>(data = data, message = message)

    class Loading<T>(data: T? = null, message: String? = null) :
        ResponseState<T>(data = data, message = message)

    class Error<T>(data: T? = null, message: String? = null) :
        ResponseState<T>(data = data, message = message)
}