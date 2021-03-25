package com.github.overlhaverde.deliverycalculator.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun <R> Flow<R>.testFlow(test: R.() -> Unit) {
    runBlocking {
        first {
            it.test()
            true
        }
    }
}