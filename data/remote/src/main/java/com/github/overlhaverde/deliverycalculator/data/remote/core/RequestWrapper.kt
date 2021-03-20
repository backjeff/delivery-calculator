package com.github.overlhaverde.deliverycalculator.data.remote.core

import com.github.overlhaverde.deliverycalculator.data.remote.model.Response

interface RequestWrapper {

    suspend fun <T> wrapperResponse(
        call: suspend () -> Response<T>
    ): Response<T>

    suspend fun <D> wrapper(
        call: suspend () -> D
    ): D
}