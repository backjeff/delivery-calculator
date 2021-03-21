package com.github.overlhaverde.deliverycalculator.feature.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

interface ViewStateListener {

    fun onViewStateError(error: Throwable)
    fun startLoading()
    fun finishLoading()

    private fun <T> ViewState<T>.handle(
        onError: ((Throwable) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {

        stateHandler(
            onSuccess = {
                finishLoading()
                onSuccess?.invoke(it)
                onComplete?.invoke()
            },
            onError = { error ->
                finishLoading()
                onError?.invoke(error) ?: onViewStateError(error)
                onComplete?.invoke()
            },
            loading = { onLoading?.invoke() ?: startLoading() }
        )
    }

    suspend fun <T> StateFlow<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        collect { viewState ->
            viewState.handle(onError, onLoading, onComplete, onSuccess)
        }
    }

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        observe(lifecycleOwner) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }
    }

}
