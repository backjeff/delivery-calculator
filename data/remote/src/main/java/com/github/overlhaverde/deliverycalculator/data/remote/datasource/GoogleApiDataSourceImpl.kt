package com.github.overlhaverde.deliverycalculator.data.remote.datasource

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.GoogleApiDataSource
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapper
import com.github.overlhaverde.deliverycalculator.data.remote.mapper.googleapi.DistanceMatrixMapper
import com.github.overlhaverde.deliverycalculator.data.remote.services.GoogleApiService
import kotlinx.coroutines.flow.flow

class GoogleApiDataSourceImpl(
    private val requestWrapper: RequestWrapper,
    private val googleApiService: GoogleApiService
): GoogleApiDataSource {

    override fun getDistanceMatrix(
        origins: String,
        destinations: String
    ) = flow {
        emit(
            DistanceMatrixMapper.toDomain(
                requestWrapper.wrapper {
                    googleApiService.getDistanceMatrix(
                        origins = origins,
                        destinations = destinations,
                        language = "pt-BR",
                        key = "AIzaSyDcxsE88c-oxVD0T7LSrRqouTRrYpY_Aws"
                    )
                }
            )
        )
    }
}