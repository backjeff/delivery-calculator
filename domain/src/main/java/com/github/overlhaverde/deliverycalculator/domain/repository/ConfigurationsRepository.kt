package com.github.overlhaverde.deliverycalculator.domain.repository

import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearchFormData
import kotlinx.coroutines.flow.Flow

interface ConfigurationsRepository {
    fun getDarkMode(): Flow<Boolean>
    fun setDarkMode(enabled: Boolean): Flow<Unit>
    fun getDistanceSearchData(): Flow<DistanceSearchFormData>
    fun setOrigin(data: String): Flow<Unit>
    fun setDestination(data: String): Flow<Unit>
    fun setKmPrice(data: String): Flow<Unit>
    fun setRoundDistance(data: Boolean): Flow<Unit>
}