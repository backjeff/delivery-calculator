package com.github.overlhaverde.deliverycalculator.feature.core

sealed class ViewState<out T> {
    object Neutral : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(
        val t: Throwable
    ) : ViewState<Nothing>()

    fun stateHandler(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        loading: () -> Unit
    ) {
        when (this) {
            is Success -> data?.let { onSuccess(it) } ?: onError(Exception("ViewState onSuccess exception"))
            is Error -> onError(t)
            is Loading -> loading()
            is Neutral -> { /* ignored */ }
        }
    }
}
