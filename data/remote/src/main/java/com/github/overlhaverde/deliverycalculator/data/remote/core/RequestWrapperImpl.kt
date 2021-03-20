package com.github.overlhaverde.deliverycalculator.data.remote.core

import com.github.overlhaverde.deliverycalculator.data.remote.model.Response
import org.koin.core.component.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class RequestWrapperImpl : RequestWrapper, KoinComponent {

//    private val sessionLocalDataSource: SessionLocalDataSource by inject()

    @Synchronized
    override suspend fun <T> wrapperResponse(
        call: suspend () -> Response<T>
    ): Response<T> = wrapper(call)

    @Synchronized
    override suspend fun <D> wrapper(
        call: suspend () -> D
    ): D = try {
            call()
        } catch (httpException: HttpException) {
            throw Exception(httpException.message())
        } catch (ioException: IOException) {
            throw ioException
        } catch (stateException: IllegalStateException) {
            throw stateException
        }
}
