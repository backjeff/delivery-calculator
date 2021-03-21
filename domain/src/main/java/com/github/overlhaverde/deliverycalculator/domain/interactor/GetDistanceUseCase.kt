package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.interactor.GetDistanceUseCase.Params
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.domain.repository.DistanceRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class GetDistanceUseCase(
    scope: CoroutineScope,
    private val distanceRepository: DistanceRepository
) : UseCase<DistanceSearch, Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        params.origins.isEmpty() -> throw InvalidParameterException("Missing origins parameter")
        params.destinations.isEmpty() -> throw InvalidParameterException("Missing destinations parameter")
        else -> distanceRepository.getDistance(
            origins = params.origins,
            destinations = params.destinations,
        )
    }

    data class Params(
        val origins: String,
        val destinations: String
    )
}
