package com.github.overlhaverde.deliverycalculator.domain.repository

import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import kotlinx.coroutines.flow.Flow

interface DistanceRepository {
    fun getDistance(
        origins: String,
        destinations: String,
    ): Flow<DistanceSearch>
}