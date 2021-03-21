package com.github.overlhaverde.deliverycalculator.feature.extensions

import com.github.overlhaverde.deliverycalculator.feature.core.ViewState
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<ViewState<T>>.postNeutral() {
    value = ViewState.Neutral
}

fun <T> MutableStateFlow<ViewState<T>>.postSuccess(data: T) {
    value = ViewState.Success(data)
}

fun <T> MutableStateFlow<ViewState<T>>.postError(error: Throwable) {
    value = ViewState.Error(error)
}

fun <T> MutableStateFlow<ViewState<T>>.postError(message: String?) {
    value = ViewState.Error(Throwable(message))
}

fun <T> MutableStateFlow<ViewState<T>>.postLoading() {
    value = ViewState.Loading
}
