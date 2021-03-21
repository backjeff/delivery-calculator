package com.github.overlhaverde.deliverycalculator.data.repository

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.GoogleApiDataSource
import com.github.overlhaverde.deliverycalculator.domain.repository.DistanceRepository

class DistanceRepositoryImpl(
    private val googleApiDataSource: GoogleApiDataSource
): DistanceRepository {
    override fun getDistance(
        origins: String,
        destinations: String,
    ) = googleApiDataSource.getDistanceMatrix(
        origins = origins,
        destinations = destinations
    )
}