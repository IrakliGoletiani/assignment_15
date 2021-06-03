package com.example.homework10

data class DataResult<T>(val status: Status, val data: T? = null, val message: String? = null, val loading: Boolean) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): DataResult<T> {
            return DataResult(Status.SUCCESS, data, null, false)
        }

        fun <T> error(message: String): DataResult<T> {
            return DataResult(Status.ERROR, null, message, false)
        }

        fun <T> loading(loading: Boolean): DataResult<T> {
            return DataResult(Status.LOADING,null,null,loading)
        }
    }

}