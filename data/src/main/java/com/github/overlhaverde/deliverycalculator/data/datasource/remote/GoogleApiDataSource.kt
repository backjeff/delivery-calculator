package com.github.overlhaverde.deliverycalculator.data.datasource.remote

import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import kotlinx.coroutines.flow.Flow

interface GoogleApiDataSource {
    fun getDistanceMatrix(
        origins: String,
        destinations: String,
    ): Flow<DistanceSearch>
}