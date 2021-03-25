package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.extensions.calculatePrice
import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase.Params
import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase.Response
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import org.koin.core.error.MissingPropertyException
import java.security.InvalidParameterException

class FormatDistanceResponseUseCase(
    scope: CoroutineScope,
    contextProvider: CoroutineContextProvider
) : UseCase<Response, Params>(scope, contextProvider) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        params.kmPrice == null -> throw MissingPropertyException("Invalid value per km")
        else -> flow {
            emit(
                Response(
                    distance = params.distanceSearch.distance.text,
                    duration = params.distanceSearch.duration.text,
                    value = params.distanceSearch.distance.value.toDouble().calculatePrice(
                        kmPriceInCents = params.kmPrice,
                        shouldRoundDistance = params.shouldRoundDistance
                    )
                )
            )
        }
    }

    data class Params(
        val kmPrice: Double?,
        val distanceSearch: DistanceSearch,
        val shouldRoundDistance: Boolean = false
    )

    data class Response(
        val distance: String,
        val duration: String,
        val value: String,
    )

}
